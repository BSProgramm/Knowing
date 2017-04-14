package ru.badsprogramm.knowing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SubjectFragment extends Fragment {

    RecyclerView rv;
    RVAmain adapter;
    List<Subject> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view;
        view = inflater.inflate(R.layout.lay_fragment, container, false);

        rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.smoothScrollToPosition(0);
        list.clear();
        list.add(new Subject("MATAN", 4, 2));
        list.add(new Subject("ALGEM", 5, 2));
        list.add(new Subject("ALGEM", 5, 4));
        list.add(new Subject("ALGEM", 4, 0));
        list.add(new Subject("ALGEM", 2, 2));
        adapter = new RVAmain(getContext(), list);
        rv.setAdapter(adapter);

        return view;
    }
}
