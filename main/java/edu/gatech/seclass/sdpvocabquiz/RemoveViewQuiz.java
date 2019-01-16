package edu.gatech.seclass.sdpvocabquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RemoveViewQuiz extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_view_quiz);
        mRecyclerView = (RecyclerView) findViewById(R.id.rm_quiz_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<String> list = new ArrayList<>();
        for(Quiz q : QuizManager.getInstance().getMyQuizzess()){
            list.add(q.getName());
        }
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

    }


}
