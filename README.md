# 安卓架构

> 博客地址：(https://blog.csdn.net/weixin_43607099)



#### 简单使用

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

		dependencies {
    	        implementation 'com.github.bigdac:BaseAdapter:1.0.3'
    	}

public class SimpleAdapter extends BaseAdapter<String> {
    private Context mContext;
    public SimpleAdapter(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
        this.mContext = context;
    }

    @Override
    protected void bindViewHolder(ViewHolder holder, final String strings, final int position) {
        //      链式调用
        holder.setText(R.id.tv_title1,strings)
                .setText(R.id.tv_title2,"哈哈哈");
        //       自定义网咯图片解析方式
        holder.setImageView(R.id.iv_pic,
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591887961825&di=b46faa0b1254616dd4f1a36653f36fef&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg"
                 , new MyImageLoader(mContext));
        //       adapter 内部的点击事件
        holder.setOnItemClick(R.id.iv_pic, new OnItemClick() {
            @Override
            public void click(View view) {
                Log.e("TAG", "click: ----------" );
                //               回调到activity中 根据type判断谁点击
                setActivityItemClick(view,strings,1,position);
            }
        });
        holder.setOnItemClick(R.id.tv_title1, new OnItemClick() {
            @Override
            public void click(View view) {
                Log.e("TAG", "click: ----------" );
                setActivityItemClick(view,strings,2,position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "click: ----------" );
                setActivityItemClick(v,strings,3,position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setActivityLongItemClick(v,strings,1,position);
                return true;
            }
        });
    }
}

#### 自定义加载网络图片
public class MyImageLoader implements ViewHolder.ImageLoader {
    private Context mContext;

    public MyImageLoader(Context context) {
        this.mContext = context;
    }

    @Override
    public void loadImage(ImageView imageView, String url) {
//      实现MyImageLoader 方法自定义网咯图片的解析方法更容易扩展
        Glide.with(mContext).load(url).into(imageView);
    }
}

#### 多布局使用


public class MixLayoutAdapter extends BaseAdapter<String> {
    private Context mContext;
    public MixLayoutAdapter(Context context, List<String> data) {
        super(context, data, new MixtureLayout<String>() {
            @Override
            public int getLayoutId(String data,int pos) {
                if ((pos+1)%2 ==0){
                    return R.layout.item_simple;
                }else {
                    return R.layout.item_simple2;
                }
            }
        });
        this.mContext = context;
    }

    @Override
    protected void bindViewHolder(ViewHolder holder, final String strings, final int position) {
//      链式调用
        Log.e("TAG", "onCreateViewHolder: -->"+ (holder.getLayoutId()==R.layout.item_simple)+ (holder.getLayoutId()==R.layout.item_simple2));
        holder.setText(R.id.tv_title1,strings)
                .setText(R.id.tv_title2,"哈哈哈");
//       自定义网咯图片解析方式
        holder.setImageView(R.id.iv_pic,
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591887961825&di=b46faa0b1254616dd4f1a36653f36fef&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg"
                 , new MyImageLoader(mContext));
//       adapter 内部的点击事件
        holder.setOnItemClick(R.id.iv_pic, new OnItemClick() {
            @Override
            public void click(View view) {
                Log.e("TAG", "click: ----------" );
//               回调到activity中 根据type判断谁点击
                setActivityItemClick(view,strings,1,position);
            }
        });
        holder.setOnItemClick(R.id.tv_title1, new OnItemClick() {
            @Override
            public void click(View view) {
                Log.e("TAG", "click: ----------" );
                setActivityItemClick(view,strings,2,position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "click: ----------" );
                setActivityItemClick(v,strings,3,position);
            }
        });
        holder.setOnLongItemClick(R.id.iv_pic, new OnLongItemClick() {
            @Override
            public void click(View view) {
                Log.e("TAG", "Longclick: ----------" );
                setActivityLongItemClick(view,strings,1,position);
            }
        });
    }


 #### 博客地址

