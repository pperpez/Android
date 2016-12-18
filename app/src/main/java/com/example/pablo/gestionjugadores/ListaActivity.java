package com.example.pablo.gestionjugadores;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static com.example.pablo.gestionjugadores.R.id.info;

public class ListaActivity extends AppCompatActivity {

    private static final int CODE1 = 1;
    private static final int CODE2 = 2;
    private ListView lista;

    private List<Jugador> listaJugadores = new ArrayList<>();

    private AdaptadorJugadores adaptador;

    private static int id;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListaActivity.this, AddEditActivity.class);
                startActivityForResult(i, CODE1);
            }
        });
        sp = getSharedPreferences("datos", MODE_PRIVATE);
        lista = (ListView)findViewById(R.id.lista);

        cargarDatos();

        adaptador = new AdaptadorJugadores(this);
        lista.setAdapter(adaptador);

        registerForContextMenu(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        LayoutInflater inflate = getLayoutInflater();
        View item = inflate.inflate(R.layout.header, null);
        menu.setHeaderView(item);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        id = info.position;
        switch (item.getItemId()){
            case R.id.opcEdit:
                Jugador j = listaJugadores.get(info.position);
                Intent i = new Intent(ListaActivity.this, AddEditActivity.class);
                i.putExtra("jugador", j);
                startActivityForResult(i, CODE2);
                break;
            case R.id.opcDel:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getResources().getString(R.string.tituloDialogo));

                builder.setPositiveButton(getResources().getString(R.string.si), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listaJugadores.remove(info.position);
                        adaptador.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CODE1:
                if(resultCode == RESULT_OK){
                    Jugador j = (Jugador) data.getSerializableExtra("jugador");
                    j.setId(listaJugadores.size() + 1);
                    listaJugadores.add(j);

                    adaptador.notifyDataSetChanged();
                }
                break;
            case CODE2:
                if(resultCode == RESULT_OK){
                    Jugador j = (Jugador) data.getSerializableExtra("jugador");
                    listaJugadores.set(id, j);
                    adaptador.notifyDataSetChanged();
                }
                break;
        }
    }

    private void cargarDatos() {
        listaJugadores.add(new Jugador(1, "Rubén Castro", "Real Betis Balompié", 35, 3, "Delantero Centro"));
        listaJugadores.add(new Jugador(2, "Joaquín", "Real Betis Balompié", 35, 2,"Mediapunta"));
        listaJugadores.add(new Jugador(3, "Piqué", "FC Barcelona", 29, 1, "Defensa Central"));
        listaJugadores.add(new Jugador(4, "Lionel Messi", "FC Barcelona", 29, 3, "Delantero Centro"));
        listaJugadores.add(new Jugador(5, "Cristiano Ronaldo", "Real Madrid", 31, 3,"Extremo derecha"));
        listaJugadores.add(new Jugador(6, "Sergio Ramos", "Real Madrid", 30, 1, "Defensa Central"));
        listaJugadores.add(new Jugador(7, "Adán", "Real Betis Balompié", 29, 0, "Portero"));
        listaJugadores.add(new Jugador(8, "Trigueros", "Villareal CF", 25, 2,"Centrocampista polivalente"));
        listaJugadores.add(new Jugador(9, "Gayá", "Valencia CF", 21,1, "Lateral izquierdo"));
        listaJugadores.add(new Jugador(10, "Sergio Rico", "Sevilla FC", 24, 0, "Portero"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_del_all) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.tituloDialogoAll));

            builder.setPositiveButton(getResources().getString(R.string.si), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listaJugadores.clear();
                    adaptador.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

            return true;
        }

        if(id == R.id.action_cerrar_sesion){
            SharedPreferences.Editor editor = sp.edit();
            editor.remove("user");
            editor.remove("password");
            editor.commit();
            Intent i = new Intent(ListaActivity.this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class AdaptadorJugadores extends ArrayAdapter{

        Activity context;

        public AdaptadorJugadores(Activity context) {
            super(context, R.layout.fila_jugador, listaJugadores);
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;

            ViewHolder holder;

            if(item == null){
                LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.fila_jugador, null);

                holder = new ViewHolder();
                holder.txtNombre = (TextView)item.findViewById(R.id.txtNombre);
                holder.txtEquipo = (TextView)item.findViewById(R.id.txtEquipo);
                holder.txtEdad = (TextView)item.findViewById(R.id.txtEdad);
                holder.txtDescripcion = (TextView)item.findViewById(R.id.txtDescripcion);
                holder.imagen = (ImageView) item.findViewById(R.id.imagen);

                item.setTag(holder);
            } else {
                holder = (ViewHolder)item.getTag();
            }

            holder.txtNombre.setText(listaJugadores.get(position).getNombre());
            holder.txtEquipo.setText(listaJugadores.get(position).getEquipo());
            holder.txtEdad.setText(String.valueOf(listaJugadores.get(position).getEdad()) + " años");
            holder.txtDescripcion.setText(listaJugadores.get(position).getDescripcion());
            switch (listaJugadores.get(position).getPosicion()){
                case 0:
                    holder.imagen.setBackgroundResource(R.mipmap.ic_portero);
                    break;
                case 1:
                    holder.imagen.setBackgroundResource(R.mipmap.ic_defensa);
                    break;
                case 2:
                    holder.imagen.setBackgroundResource(R.mipmap.ic_mediocentro);
                    break;
                case 3:
                    holder.imagen.setBackgroundResource(R.mipmap.ic_delantero);
                    break;
            }

            return item;
        }
    }

    static class ViewHolder{
        TextView txtNombre;
        TextView txtEquipo;
        TextView txtEdad;
        TextView txtDescripcion;
        ImageView imagen;
    }
}
