package com.andrea.com.bakingtime.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrea.com.bakingtime.Activity.ViewStepActivity;
import com.andrea.com.bakingtime.Model.RecipeTable;
import com.andrea.com.bakingtime.R;

public class InstructionFragment extends Fragment {

    private String instruction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            int stepNo = getArguments().getInt(ViewStepActivity.FRAGMENT_KEY_DATA);
        getData(stepNo);}
    }

    private void getData(int id){
        Context context = getActivity().getApplicationContext();
        String[] selectionArgs = {Integer.toString(id)};
        Cursor cursor = context.getContentResolver().query(RecipeTable.CONTENT_URI,
                null,
                RecipeTable.FIELD_COL_ID + " = ?",
                selectionArgs,
                null
        );
        cursor.moveToFirst();
        instruction = cursor.getString(cursor.getColumnIndex(RecipeTable.FIELD_COL_DESCRIPTION));
        cursor.close();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instructions_layout, container, false);
        TextView tv = view.findViewById(R.id.steps_detail_tv);
        if (instruction == null){
            tv.setText("No recipe is found");
        } else {
        tv.setText(instruction);}
        return view;
    }


}
