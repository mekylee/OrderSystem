package com.example.ordersystem.customview;

import java.util.jar.Attributes;

import com.example.ordersystem.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class CleanableEditText extends EditText {
    private Context eContext;
    private TextWatcherCallBack eCallBack;  //回调函数
    private Drawable eDrawable; //右侧删除图标
    //回调接口
  	public interface TextWatcherCallBack{
  		public void handleMoreTextChanged();
  	}
	public CleanableEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	    this.eContext=context;
	    init();
	}
	
    public CleanableEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	    this.eContext=context;
	    init();
    }

	public CleanableEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	    this.eContext=context;
	    init();
	}

	public void init(){
    	eDrawable=eContext.getResources().getDrawable(R.drawable.clear);
    	eCallBack=null;
    	TextWatcher textWatcher=new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				//更新状态，检查是够显示删除按钮
				updateCleanable(length(),true);
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				//更新状态,检查是否显示删除按钮
				updateCleanable(length(), true);
				//如果有在activity中设置回调，则此处可以触发
				if(eCallBack!=null){
					eCallBack.handleMoreTextChanged();
				}
			}
		};
		this.addTextChangedListener(textWatcher);
		this.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				//更新状态,检查是否显示删除按钮
				updateCleanable(length(), arg1);
			}
		});
    }
    
	//当内容不为空且获得焦点时，才显示右侧删除按钮
	public  void updateCleanable(int length, boolean hasFocus) {
		if(length()>0 && hasFocus){
			setCompoundDrawablesWithIntrinsicBounds(null, null, eDrawable, null);
		}else{
			setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		final int DAWABLE_RIGHT=2;
		//可以或得上下左右四个drawable,右侧第二。图标没有设置为空
		Drawable rightIcon=getCompoundDrawables()[DAWABLE_RIGHT];
		if(rightIcon!=null &&event.getAction()==MotionEvent.ACTION_UP){
			//检查点击的位置是否是右侧的删除图标
			//注意，使用getRightX()是获取屏幕相对位置，getX()可能获取相对父组件的位置
			int leftEdgeOfRightDrawable=getRight()-getPaddingRight()-rightIcon.getBounds().width();
			if(event.getRawX()>=leftEdgeOfRightDrawable){
				setText("");
			}
		}
		return super.onTouchEvent(event);
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		eDrawable=null;
		super.finalize();
	}
	

	
    
}
