package br.com.digitalhouse.appmytasks.views.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.appmytasks.R;
import br.com.digitalhouse.appmytasks.adapter.RecyclerViewTarefaAdapter;
import br.com.digitalhouse.appmytasks.data.Database;
import br.com.digitalhouse.appmytasks.data.TarefaDao;
import br.com.digitalhouse.appmytasks.model.Tarefa;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewTarefaAdapter adapter;
    private List<Tarefa> tarefaList = new ArrayList<>();
    private TarefaDao tarefaDao;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);

        //TODO: Fazer as chamadas necessárias quando inicializado o fragmento

        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewUltimasTarefas);
        adapter = new RecyclerViewTarefaAdapter(tarefaList);
        tarefaDao = Database.getDatabase(getContext()).tarefaDao();
        //TODO: inicializar o DAO
    }

    //TODO: Desenvolver o método que busca as tarefas em ordem decrecente com um limite de 5 itens

    private void buscarTodosTarefasRecentes(){
        tarefaDao.getAllTarefasLimitadas()
                //SubscribeOn: determina em qual thread o observable irá emitir os dados
                .subscribeOn(Schedulers.io())
                //ObserveOn: determina em qual thread será executa quando os dados forem emitidos
                .observeOn(AndroidSchedulers.mainThread())
                //Assina o observer ao observable
                //Se for sucesso irá pegar a lista de produtos e setar no método do adapter que atualiza a lista de produtos
                .subscribe(tarefas -> {
                            adapter.atualizaListaTarefa(tarefas);
                        },

                        //Senão irá lançar uma exceção
                        throwable -> {
                            Log.i("TAG", "método getAllTarefas" + throwable.getMessage());
                        });





    }
}
