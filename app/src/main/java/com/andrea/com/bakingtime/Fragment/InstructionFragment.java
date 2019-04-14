package com.andrea.com.bakingtime.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.andrea.com.bakingtime.R;

public class InstructionFragment extends Fragment {

    private String[] instructions;

    private TextView tv;
    private int stepNo = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instructions_layout, container, false);
        tv = view.findViewById(R.id.steps_detail_tv);
        if (instructions == null){
            tv.setText("No recipe is found");
        } else {
        tv.setText(instructions[stepNo]);}

        Button nextButton = view.findViewById(R.id.steps_detail_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stepNo < instructions.length-2){stepNo = stepNo + 1;}
                else {stepNo = 1;}
                tv.setText(instructions[stepNo]);
            }
        });

        return view;
    }

    public void setData (String[] dataset){
        instructions = dataset;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        stepNo = 1;
    }
}
