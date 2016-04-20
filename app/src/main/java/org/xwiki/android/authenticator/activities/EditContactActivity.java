package org.xwiki.android.authenticator.activities;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.xwiki.android.authenticator.R;


/**
 * A login screen that offers login via email/password.
 */
public class EditContactActivity extends AppCompatActivity {

    // UI references.
    private EditText mFullNameView;
    private EditText mEmailView;
    private EditText mCellPhoneView;
    private EditText mWorkPhoneView;

    private TextView mContactInfoTextView;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_edit_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        StatusBarColorCompat.compat(this, Color.parseColor("#0077D9"));

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mFullNameView = (EditText) findViewById(R.id.fullname);
        mCellPhoneView = (EditText) findViewById(R.id.cellphone);
        mWorkPhoneView = (EditText) findViewById(R.id.workphone);

        mUri = getIntent().getData();
        mContactInfoTextView = (TextView) findViewById(R.id.contactinfo);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }else if(item.getItemId()==R.id.action_save){
            boolean flag=save();
            if(flag==true) {
                finish();
            }else{
                Toast.makeText(EditContactActivity.this,"Please Check Again",Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void updataCotact(Uri uri) {
        long  rawContactId = 0 ;
        ContentResolver cr = getContentResolver();
        Cursor people = cr.query(uri, new String[]{ContactsContract.Contacts.Data.RAW_CONTACT_ID}, null, null, null);
        if(people.getCount()>0){
            while (people.moveToNext()) {
                rawContactId = people.getLong(people.getColumnIndex(ContactsContract.Data.RAW_CONTACT_ID));
            }
        }

        Log.i("rawContactId", rawContactId+"");
//        RawContact rawContact = ContactManager.getRawContact(getApplicationContext(), rawContactId);
//        BatchOperation batchOperation = new BatchOperation(getApplicationContext(), getContentResolver());
//        rawContact.mFirstName = mFullNameView.getText().toString();
//        rawContact.mFullName = mFullNameView.getText().toString();
//        rawContact.mCellPhone = mCellPhoneView.getText().toString();
//        rawContact.mEmail = mEmailView.getText().toString();
//        Log.i("edit",rawContact.toString());
//        ContactManager.updateContactLocal(this, getContentResolver(), rawContact, rawContactId);

//        ContactManager.updateContact(getApplicationContext(),getContentResolver(),rawContact,false,false,false,true,rawContactId,batchOperation);

    }

    private boolean save(){
        updataCotact(mUri);
        return true;
    }



}

