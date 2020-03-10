/**
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.huawei.hms.mlkit.sample.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.huawei.hms.mlkit.sample.R;

public class AddPictureDialog extends Dialog implements View.OnClickListener {
    private TextView tvTakePicture;
    private TextView tvSelectImage;
    private TextView tvCancel;
    private Context context;
    private ClickListener clickListener;

    public interface ClickListener {
        void takePicture();

        void selectImage();
    }

    public AddPictureDialog(Context context) {
        super(context, R.style.MyDialogStyle);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initViews();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.dialog_add_picture, null);
        this.setContentView(view);

        this.tvTakePicture = view.findViewById(R.id.take_photo);
        this.tvSelectImage = view.findViewById(R.id.select_image);
        this.tvCancel = view.findViewById(R.id.cancel);

        this.tvTakePicture.setOnClickListener(this);
        this.tvSelectImage.setOnClickListener(this);
        this.tvCancel.setOnClickListener(this);

        this.setCanceledOnTouchOutside(true);
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(layoutParams);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_photo:
                this.clickListener.takePicture();
                this.dismiss();
                break;
            case R.id.select_image:
                this.clickListener.selectImage();
                this.dismiss();
                break;
            case R.id.cancel:
                this.dismiss();
                break;
        }
    }
}

