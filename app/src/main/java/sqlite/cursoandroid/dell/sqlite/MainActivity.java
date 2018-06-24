package sqlite.cursoandroid.dell.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLData;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("app", MODE_PRIVATE, null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Pessoas(nome VARCHAR(200), idade INT(3))");

            sqLiteDatabase.execSQL("INSERT INTO Pessoas (nome, idade) VALUES ('Julio', 28)");
            sqLiteDatabase.execSQL("INSERT INTO Pessoas (nome, idade) VALUES ('Larissa', 22)");

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT nome, idade FROM Pessoas", null);

            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while (cursor != null) {
                cursor.getString(indiceIdade);
                Toast.makeText(getApplicationContext(), cursor.getString(indiceNome), Toast.LENGTH_SHORT).show();
                Log.i("resultado Nome", cursor.getString(indiceNome));
                Log.i("resultado Idade", cursor.getString(indiceIdade));

                cursor.moveToNext();
            }
        } catch (Exception e) {
            Log.i("erro", "erro");
        }
    }
}
