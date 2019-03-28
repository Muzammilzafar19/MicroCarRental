package design.ws.com.MicroCarRental;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookNowActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnSubmit;
    private ProgressDialog pDialog;
    private EditText txtemail,txtFullName,txtphone,comment;
    private Spinner spinner1,spinner2,spinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        toolbar =  findViewById(R.id.toolbar);
        btnSubmit=findViewById(R.id.btnSubmit);
        txtemail=findViewById(R.id.email);
        spinner1=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        spinner3=findViewById(R.id.spinner3);
        txtFullName=findViewById(R.id.Full_Name);
        txtphone=findViewById(R.id.phone);
        comment=findViewById(R.id.comment);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmail(txtemail.getText().toString())&& !txtFullName.getText().toString().equals("Full Name") && !txtFullName.getText().toString().equals("") && !txtphone.getText().toString().equals("Phone No.") && !spinner1.getSelectedItem().toString().equals("PickUp Location") && !spinner2.getSelectedItem().toString().equals("Drop Location") && !spinner3.getSelectedItem().toString().equals("Select Car"))
                {
                    String body="Customer Name: "+txtFullName.getText().toString()+"\n"+" Customer Email:"+txtemail.getText().toString()+"\n"+"Phone No: "+txtphone.getText().toString()+"\n"+"PickUp Location: "+spinner1.getSelectedItem().toString()+"\n"+"Drop Location: "+spinner2.getSelectedItem().toString()+"\n"+"Car Selected: "+spinner3.getSelectedItem().toString()+"\n"+" Comments: "+comment.getText().toString();

                  //  sendMessage();
                    new SendMailPhp().execute("http://iiuiofficial.com/sendmail.php?frommail="+txtemail.getText().toString()+"&messagebody="+"Customer Name: "+txtFullName.getText().toString()+"</br>"+" Customer Email:"+txtemail.getText().toString()+"</br>"+"Phone No: "+txtphone.getText().toString()+"</br>"+"PickUp Location: "+spinner1.getSelectedItem().toString()+"</br>"+"Drop Location: "+spinner2.getSelectedItem().toString()+"</br>"+"Car Selected: "+spinner3.getSelectedItem().toString()+"</br>"+" Comments: "+comment.getText().toString());

                }
                else {
                    Toast.makeText(BookNowActivity.this,"Request not Submitted Successfully",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void sendMessage() {
        final ProgressDialog dialog = new ProgressDialog(BookNowActivity.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("microcarrentalisb@gmail.com", "&,aJoZ*c;mP0");
                    sender.sendMail("Car booking request",
                            "Customer Name: "+txtFullName.getText().toString()+"\n"+" Customer Email:"+txtemail.getText().toString()+"\n"+"Phone No: "+txtphone.getText().toString()+"\n"+"PickUp Location: "+spinner1.getSelectedItem().toString()+"\n"+"Drop Location: "+spinner2.getSelectedItem().toString()+"\n"+"Car Selected: "+spinner3.getSelectedItem().toString()+"\n"+" Comments: "+comment.getText().toString(),
                            txtemail.getText().toString(),"microcarrentalisb@gmail.com");

                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
    public boolean isEmail(String email)
    {
        boolean matchFound1;
        boolean returnResult=true;
        email=email.trim();
        if(email.equalsIgnoreCase(""))
            returnResult=false;
        else if(!Character.isLetter(email.charAt(0)))
            returnResult=false;
        else
        {
            Pattern p1 = Pattern.compile("^\\.|^\\@ |^_");
            Matcher m1 = p1.matcher(email.toString());
            matchFound1=m1.matches();

            Pattern p = Pattern.compile("^[a-zA-z0-9._-]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z]{2,4}$");
            // Match the given string with the pattern
            Matcher m = p.matcher(email.toString());

            // check whether match is found
            boolean matchFound = m.matches();

            StringTokenizer st = new StringTokenizer(email, ".");
            String lastToken = null;
            while (st.hasMoreTokens())
            {
                lastToken = st.nextToken();
            }
            if (matchFound && lastToken.length() >= 2
                    && email.length() - 1 != lastToken.length() && matchFound1==false)
            {

                returnResult= true;
            }
            else returnResult= false;
        }
        return returnResult;
    }
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(BookNowActivity.this,MainActivity.class));


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        //region Description
        switch (menuItem.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
        //endregion
    }
    public  class  SendMailPhp extends AsyncTask<String,Integer,String> {
        String status="";

        @Override
        protected String doInBackground(String... connUrl) {
            HttpURLConnection conn;
            BufferedReader buffer;
            try{
                final URL url=new URL(connUrl[0]);
                conn=(HttpURLConnection)url.openConnection();
                conn.addRequestProperty("Content-Type","application/json; charset=utf-8");
                conn.setRequestMethod("GET");
                int response=conn.getResponseCode();
                if(response==200)
                {
                    InputStream in=new BufferedInputStream(conn.getInputStream());
                    buffer=new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line=buffer.readLine())!=null)
                    {
                        status=line;
                    }
                }
            }
            catch (Exception ex){
                ex.getMessage();
            }
            return status;
        }
        @Override
        protected void onPreExecute(){
            pDialog = new ProgressDialog(BookNowActivity.this);
            pDialog.setTitle("Sending Mail");
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            if(result!=null)
            {
                try{
Toast.makeText(BookNowActivity.this,result.toString(),Toast.LENGTH_SHORT).show();
                    if (pDialog.isShowing())
                        pDialog.dismiss();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            else {
                Toast.makeText(BookNowActivity.this,"Could not get result",Toast.LENGTH_LONG).show();
            }
        }

    }

    ///////////////////////////////////////////////////////////////////////////

}
