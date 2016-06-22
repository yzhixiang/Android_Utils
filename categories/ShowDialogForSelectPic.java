// 显示选择系统图库 相机对话框
private void showDialogCustom() {
    AlertDialog.Builder builder = new AlertDialog.Builder(
            MainActivity.this);
    builder.setTitle("选择：");
    builder.setItems(mCustomItems,
            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (0 == which) {
                        // 本地图册
                        Intent intent = new Intent(
                                Intent.ACTION_PICK, null);
                        intent.setDataAndType(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                IMAGE_TYPE);
                        startActivityForResult(intent, RESULT_IMAGE);
                    } else if (1 == which) {
                        // 系统相机
                        Intent intent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        Uri photoUri = Uri.fromFile(
                                new File(TEMP_IMAGE_PATH));
                        intent.putExtra(
                                MediaStore.EXTRA_OUTPUT,
                                photoUri);
                        startActivityForResult(intent, RESULT_CAMERA);
                    }
                }
            });
    builder.create().show();
}