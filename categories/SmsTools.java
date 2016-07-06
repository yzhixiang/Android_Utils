package com.itheima.mobilesafe.utils;

import java.io.File;
import java.io.FileOutputStream;

import org.xmlpull.v1.XmlSerializer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Xml;

public class SmsTools {
	
	//B ����ʦ����һ���ӿ� �� ��¶һЩ�ص�������
	
	public interface BackUpCallBack{
		/**
		 * �����ű���ǰ���õķ���
		 * @param total  �ܵĶ��Ÿ���
		 */
		public void beforeSmsBackup(int total);
		
		/**
		 * ���ű����е��õķ��� 
		 * @param progress ��ǰ���ݵĽ��ȡ�
		 */
		public void onSmsBackup(int progress);
	}
	
	
	/**
	 * ���ݶ���
	 * @param context ������
	 * @param path ���ű��ݺ��ļ���·��
	 * @param pb ������
	 * @param pd �������Ի���
	 * 
	 */
	public static void backup(Context context,String path,BackUpCallBack backupCallback) throws Exception{
		XmlSerializer serializer = Xml.newSerializer();
		
		//ָ�����к����Ĳ���
		File file = new File(path);
		FileOutputStream fos = new FileOutputStream(file);
		serializer.setOutput(fos, "utf-8");
		serializer.startDocument("utf-8", true);
		serializer.startTag(null, "smss");
		ContentResolver resolver = context.getContentResolver();
		Uri uri = Uri.parse("content://sms/");
		Cursor cursor = resolver.query(uri, new String[]{"address","date","type","body"}, null, null, null);
		backupCallback.beforeSmsBackup(cursor.getCount());
		//pd.setmax(cursor.getcount());
		int progress = 0;
		while(cursor.moveToNext()){
			serializer.startTag(null, "sms");
			serializer.startTag(null, "address");
			String address = cursor.getString(0);
			serializer.text(address);
			serializer.endTag(null, "address");
			
			serializer.startTag(null, "date");
			String date = cursor.getString(1);
			serializer.text(date);
			serializer.endTag(null, "date");
			
			serializer.startTag(null, "type");
			String type = cursor.getString(2);
			serializer.text(type);
			serializer.endTag(null, "type");
			
			serializer.startTag(null, "body");
			String body = cursor.getString(3);
			serializer.text(body);
			serializer.endTag(null, "body");
			
			serializer.endTag(null, "sms");
			
			//�������ߣ�ģ���кܶ�������
			Thread.sleep(1000);
			progress++;
			//pb.setProgress(progress);
			backupCallback.onSmsBackup(progress);
		}
		cursor.close();
		serializer.endTag(null, "smss");
		serializer.endDocument();
		fos.close();
	}
}



//��UI�е�ʹ��ʾ��,�����ڱ������ࡣ
/**
 * ���ŵı���
 * 
 * @param view
 */
public void smsBackup(View view) {
	if (Environment.getExternalStorageState().equals(
			Environment.MEDIA_MOUNTED)) {
		final File file = new File(
				Environment.getExternalStorageDirectory(), "smsbackup.xml");
		final ProgressDialog pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMessage("�԰����꣬���ڱ�����...");
		pd.show();
		new Thread() {
			public void run() {
				try {
					SmsTools.backup(getApplicationContext(), file.getAbsolutePath(), new BackUpCallBack() {
						@Override
						public void onSmsBackup(int progress) {
							pd.setProgress(progress);
						}
						
						@Override
						public void beforeSmsBackup(int total) {
							pd.setMax(total);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
				pd.dismiss();
			};
		}.start();
	} else {
		Toast.makeText(this, "sd������", 0).show();
	}
}
