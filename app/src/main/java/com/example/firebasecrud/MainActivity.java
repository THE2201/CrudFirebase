package com.example.firebasecrud;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Create
        String personId = "person1";
        createPerson(personId, "Abel", "Ernesto", "john.doe@example.com", "22/01/2024");
        createPerson(personId, "Abel1", "Ernesto1", "john.correo@correo.com", "22/01/2006");

        //Read
        readPerson(personId);

       //Update
        updatePerson(personId,"Nuevo nombre", "nuevo apellido", "correo@coore.com", "22/06/2006");

        //Delete
        deletePerson(personId);
    }

    // Create a new Person record
    private void createPerson(String personId, String name, String sname, String email, String dob) {
        Person person = new Person(name,  sname,  email,  dob);
        mDatabase.child("persons").child(personId).setValue(person);
    }

    // Read a single Person record
    private void readPerson(String personId) {
        mDatabase.child("persons").child(personId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Person person = dataSnapshot.getValue(Person.class);
                if (person != null) {
                    System.out.println("Person Nombre: " + person.getName() + ", Apellido: " + person.getSname() + ", email: " + person.getEmail() + ", DOB: " + person.getDob());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error al leer persona: " + databaseError.toException());
            }
        });
    }

    // Update an existing Person record
    private void updatePerson(String personId, String name, String sname, String email, String dob) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", name);
        updates.put("sname", sname);
        updates.put("email", email);
        updates.put("dob", dob);

        mDatabase.child("persons").child(personId).updateChildren(updates);
    }

    // Delete a Person record
    private void deletePerson(String personId) {
        mDatabase.child("persons").child(personId).removeValue();
    }


}
