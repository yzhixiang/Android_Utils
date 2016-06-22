// 返回码：系统图库
private static final int RESULT_IMAGE = 100;
// 返回码：相机
private static final int RESULT_CAMERA = 200;
// IMAGE TYPE
private static final String IMAGE_TYPE = "image/*";
// Temp照片路径
public static String TEMP_IMAGE_PATH = 
                Environment.getExternalStorageDirectory().getPath() + "/temp.png";;

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

	/*
		在onActivityResult方法中来判断是选择的本地相册还是相机

		如果是本地相册的话，通过下面语句来获取选取招聘的路径
    	String imagePath = cursor.getString(cursor.getColumnIndex("_data"));

    	如果是相机的话，因为之前报错在指定的路径，所以可以直接取出
    */
    
}