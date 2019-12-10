package com.example.prueba1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba1.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

public class loginActivity2 extends AppCompatActivity {

    public static String base_url="http://10.0.0.11/";
    //Inicialización de la variable de autenticación de Firebase
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    //Inicialización del Gestor de llamadas
    private CallbackManager cbm = CallbackManager.Factory.create();
    //Intanciación de variables de Google SignIn
    private static int RC_SIGN_IN = 1;
    private GoogleSignInClient gsic;
    private GoogleSignInOptions gsio;

    private User user;
    private FirebaseUser currentUser;
    private SignInButton google_button = findViewById(R.id.google_button);
    private Button signin = findViewById(R.id.signin);
    private TextView register = findViewById(R.id.register);
    private LoginButton facebook_button = findViewById(R.id.facebook_button);
    private TextView email = findViewById(R.id.email) ;
    private TextView password = findViewById(R.id.password);


    //Metodo ejecutado al iniciar la Activity
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            startActivity(
                    new Intent(this, menuPrincipal.class)
            );
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        //Initializa Firebase Database
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        //Instanciación de la Activity y su Layout
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.act_login);
        googleSetUp();
        setupUI();
    }

    // ========================= AUTENTICACIÓN CON FACEBOOK Y FIREBASE ============================
    public void signInFacebook(LoginButton facebookBtn) {
        facebookBtn.registerCallback(cbm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("SUCCESS", "facebook:onSuccess:$loginResult");
                //setFacebookData(loginResult);
                firebaseAuthWithFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("CANCEL", "facebook:onCancel");
                google_button.setEnabled(true);
                facebook_button.setEnabled(true);
                register.setEnabled(true);
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("ERROR", "facebook:onError", error);
                google_button.setEnabled(true);
                facebook_button.setEnabled(true);
                register.setEnabled(true);
            }
        });
    }

    /*private void setFacebookData(LoginResult loginResult) {
        val request = GraphRequest.newMeRequest(
                loginResult.accessToken
        ) { `object`, response ->
            try {
                Log.i("Response", response.toString())
                email = response.jsonObject.getString("email")
            } catch (e:JSONException) {

            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,email,first_name,last_name")
        request.parameters = parameters
        request.executeAsync()
    }*/

    private void firebaseAuthWithFacebook(AccessToken token) {
        Log.d("TOKEN", "handleFacebookAccessToken:$token");
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SUCCESS", "signInWithCredential:success");
                            storeDatabaseUser();
                        } else {
                            google_button.setEnabled(true);
                            facebook_button.setEnabled(true);
                            register.setEnabled(true);
                            Log.w("ERROR", "Error al iniciar sesion", task.getException());
                            Toast.makeText(getApplicationContext(), "Failed Init with Facebook", Toast.LENGTH_LONG).show();
                            currentUser = null;
                            LoginManager.getInstance().logOut();
                        }
                    }
                });
        }
    //=========================================================================================


    //===================== INICIO DE SESION CON GOOGLE Y FIREBASE ============================
    private void googleSetUp() {
    gsio = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        gsic = GoogleSignIn.getClient(this, gsio);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("SUCCESS", "signInWithCredential:success");
                    storeDatabaseUser();
                } else {
                    google_button.setEnabled(true);
                    facebook_button.setEnabled(true);
                    register.setEnabled(true);
                    Log.w("ERROR", "signInWithCredential:failure", task.getException());
                    Toast.makeText(
                            getApplicationContext(),
                            "No se ha podido iniciar sesión",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }
        });
    }

    public void firebaseAuthWithEmailPass(){
        auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Success", "createUserWithEmail:success");
                            storeDatabaseUser();
                        } else {
                            google_button.setEnabled(true);
                            facebook_button.setEnabled(true);
                            register.setEnabled(true);
                            Log.w("ERROR", "signInWithCredential:failure", task.getException());
                            Toast.makeText(
                                    getApplicationContext(),
                                    "No se ha podido iniciar sesión",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
                });
    }

    private void signInGoogle() {
        Intent signInIntent = gsic.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //================================================================================

    // ============ METODO SOBREESCRITO QUE RECIBE EL ACTIVITY RESULT ============================
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                google_button.setEnabled(true);
                facebook_button.setEnabled(true);
                register.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Google sign in failed:(", Toast.LENGTH_LONG).show();
                Log.w("ERROR", "Google sign in failed", e);
            }
        } else {
            cbm.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void setupUI() {
        google_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                google_button.setEnabled(false);
                facebook_button.setEnabled(false);
                register.setEnabled(false);
                signInGoogle();
            }
        });
        facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebook_button.setEnabled(false);
                google_button.setEnabled(false);
                register.setEnabled(false);
                signInFacebook(facebook_button);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                google_button.setEnabled(false);
                facebook_button.setEnabled(false);
                register.setEnabled(false);
                firebaseAuthWithEmailPass();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
            }
        });
    }

    //Función encargada de agregar un nodo a Realtime Database con la información del usuario
    public void storeDatabaseUser() {
        currentUser = auth.getCurrentUser(); //Declarando el usuario obtenido de Firebase
        //Obtencion de el usuario, si este existe, si no, lo crea en la RealTimeDatabase
        ref.child("users").child(currentUser.getUid())//Se declara la referencia, en el nodo Users, y en el nodo con ID del User se insertara el objeto
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.exists()) {//Si no existe el usuario en la Realtime DB, lo crea y lo almacena
                            user = new User(currentUser.getUid(),currentUser.getDisplayName(),currentUser.getEmail());
                            ref.child("users").child(user.getUserId()).setValue(user);//Referencia -> Nodo Users -> Nodo User ID -> User Object
                        } else {
                            //Si ya existe el usuario, se inicia el intent del Dashboard y se culmina this
                            startActivity(new Intent(getApplicationContext(), menuPrincipal.class));
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

}