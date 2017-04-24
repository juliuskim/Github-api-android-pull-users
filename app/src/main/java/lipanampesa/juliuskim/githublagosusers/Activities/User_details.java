package lipanampesa.juliuskim.githublagosusers.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lipanampesa.juliuskim.githublagosusers.R;

public class User_details extends Activity {
    TextView about;
    ImageView pic;
    String imageurl;
    FloatingActionButton smsbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

       // setActionBar(toolbar);
        about=(TextView)findViewById(R.id.about);
        smsbtn=(FloatingActionButton)findViewById(R.id.floatingActionButton) ;
        final String name= getIntent().getStringExtra("name");

        final String profileurl= getIntent().getStringExtra("profileurl");
        about.setText("Name:"+name+"\n"+"Profile url "+"\n"+profileurl);

        imageurl=getIntent().getStringExtra("imageurl");

        smsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareBody = "Check out this awesome developer"+name+"\n"+profileurl;
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "share"));
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setTitle(name);

        pic=(ImageView)findViewById(R.id.pic);
        picassoLoader(this,pic,imageurl);
    }
    public void picassoLoader(Context context, ImageView imageView, String url){
        Log.d("PICASSO", "loading image");
        Picasso.with(context)
                .load(url)
                //.resize(30,30)
                // .placeholder(R.drawable.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
