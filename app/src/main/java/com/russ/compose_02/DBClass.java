package com.russ.compose_02;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by w0091766 on 4/29/2016.
 */
public class DBClass extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "TEST_DB.db";
    public static final String TABLE_NAME = "pokemons";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NUM = "num";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_POWER_LEVEL = "powerLevel";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ACCESS_COUNT = "accessCount";

    public DBClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("Save_v03", "DB onCreate()");

//        db.execSQL("CREATE TABLE sample_table (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, str_col VARCHAR(256), num_col INTEGER)");
//
//        db.execSQL("INSERT INTO sample_table(str_col,num_col) VALUES('Ford', 100)");
//        db.execSQL("INSERT INTO sample_table(str_col,num_col) VALUES('Toyota', 200)");
//        db.execSQL("INSERT INTO sample_table(str_col,num_col) VALUES('Honda', 300)");
//        db.execSQL("INSERT INTO sample_table(str_col,num_col) VALUES('GM', 400)");

        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_NUM + " INTEGER, " +
                COLUMN_POWER_LEVEL + " INTEGER, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_ACCESS_COUNT + " INTEGER DEFAULT 0," +
                COLUMN_IMAGE + " TEXT )";
        db.execSQL(createTable);

        insertPokemonsInDb(db);
    }

    private void insertPokemonsInDb(SQLiteDatabase db) {
        List<PokemonDataClass> pokemonList = new ArrayList<>();
        pokemonList.add(new PokemonDataClass(1, "Squirtle", 1001, 1000, getDescription("Squirtle"),0, "https://assets.pokemon.com/assets/cms2/img/pokedex/full//007.png"));
        pokemonList.add(new PokemonDataClass(2, "Metapod", 1002, 500, getDescription("Metapod"), 0, "https://assets.pokemon.com/assets/cms2/img/pokedex/full//011.png"));
        pokemonList.add(new PokemonDataClass(3, "Blastoise", 1003, 800, getDescription("Blastoise"), 0, "https://assets.pokemon.com/assets/cms2/img/pokedex/full//009.png"));
        pokemonList.add(new PokemonDataClass(4, "Butterfree", 1004, 2000, getDescription("Butterfree"), 0, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/detail/012.png"));
        pokemonList.add(new PokemonDataClass(5, "Wartortle", 1005, 1500, getDescription("Wartortle"), 0, "https://assets.pokemon.com/assets/cms2/img/pokedex/full//011.png"));
        pokemonList.add(new PokemonDataClass(6, "Bulbasaur", 1006, 1200, getDescription("Bulbasaur"), 0, "https://assets.pokemon.com/assets/cms2/img/pokedex/full//001.png"));
        pokemonList.add(new PokemonDataClass(7, "Venusaur", 1007, 1100, getDescription("Venusaur"), 0, "https://assets.pokemon.com/assets/cms2/img/pokedex/full//003.png"));
        pokemonList.add(new PokemonDataClass(8, "Charmeleon", 1008, 3000, getDescription("Charmeleon"), 0, "https://assets.pokemon.com/assets/cms2/img/pokedex/full//005.png"));

        for (PokemonDataClass pokemonDataClass : pokemonList) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, pokemonDataClass.getName());
            values.put(COLUMN_NUM, pokemonDataClass.getNum());
            values.put(COLUMN_POWER_LEVEL, pokemonDataClass.getPowerLevel());
            values.put(COLUMN_DESCRIPTION, pokemonDataClass.getDescription());
            values.put(COLUMN_ACCESS_COUNT, pokemonDataClass.getAccessCount());
            values.put(COLUMN_IMAGE, pokemonDataClass.getImage());
            db.insert(TABLE_NAME, null, values);
        }
    }

    private String getDescription(String type) {
        if (type.equals("Squirtle")){

            return "Squirtle, known as Zenigame in Japan, is a Pokémon species in Nintendo and Game Freak's Pokémon franchise. It was designed by Atsuko Nishida. Its name was changed from Zenigame to Squirtle during the English localization of the series in order to give it a clever and descriptive name.";

        }else if (type.equals("Metapod")){

            return "Metapod is a Bug-type Pokémon introduced in Generation I. It is the evolved form of Caterpie.";

        }else if (type.equals("Blastoise")){

            return "Blastoise is a Water-type Pokémon introduced in Generation I. Blastoise is the evolved form of Wartortle, and the final evolution of Squirtle. It is also the version mascot for Pokémon Blue.";

        }else if (type.equals("Butterfree")){

            return "Butterfree is a Pokémon species in Nintendo and Game Freak's Pokémon media franchise, and the evolved form of Metapod, an evolution of the Pokémon Caterpie.";

        }else if (type.equals("Bulbasaur")){

            return "Bulbasaur, known as Fushigidane in Japan, is a fictional Pokémon species in Nintendo and Game Freak's Pokémon franchise. First introduced in the video games Pokémon Red and Blue, it was created by Atsuko Nishida with the design finalized by Ken Sugimori.";

        }else if (type.equals("Venusaur")){

            return "Venusaur is a Grass/Poison-type Pokémon. It is the mascot for Pokémon Green and Pokémon LeafGreen and the final form of Bulbasaur.";

        }else if (type.equals("Charmeleon")){

            return "Charmeleon is a small, bipedal, dinosaur-like Pokémon with an appearance similar to that of its pre-evolved form, Charmander. Charmeleon differs from Charmander in that it has a much darker red skin color, a larger body structure, claws, and a horn-like protrusion on the back of its head, similar to that of an ornithopod's. Its claws are razor sharp and its tail is very strong.";

        }else{

            return "Wartortle is a Water-type Pokémon introduced in Generation I. It is the evolved form of Squirtle.";
        }
    }

    public void increaseAccessCount(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COLUMN_ACCESS_COUNT + " = " + COLUMN_ACCESS_COUNT + " + 1 WHERE " + COLUMN_ID + " = " + id);
    }

    public List<PokemonDataClass> getAllPokemons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        List<PokemonDataClass> pokemonList = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            int num = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUM));
            int powerLevel = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_POWER_LEVEL));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
            int accessCount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ACCESS_COUNT));
            String image = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE));
            pokemonList.add(new PokemonDataClass(id, name, num, powerLevel, description, accessCount, image));
        }
        cursor.close();
        return pokemonList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        Log.d("Save_v03", "DB onUpgrade() to version " + DATABASE_VERSION);
        db.execSQL("DROP TABLE IF EXISTS sample_table");
        onCreate(db);
    }

}
