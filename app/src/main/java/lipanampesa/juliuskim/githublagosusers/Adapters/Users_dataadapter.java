package lipanampesa.juliuskim.githublagosusers.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import lipanampesa.juliuskim.githublagosusers.Activities.User_details;
import lipanampesa.juliuskim.githublagosusers.R;
import lipanampesa.juliuskim.githublagosusers.models.CircleTransform;
import lipanampesa.juliuskim.githublagosusers.models.User;

/**
 * Created by julius kim on 3/6/2017.
 */

public class Users_dataadapter extends RecyclerView.Adapter<Users_dataadapter.ViewHolder> {
    private ArrayList<User> users;
    private Context context;



    public Users_dataadapter(Context context, ArrayList<User> users) {
        this.users =users;
        this.context = context;
    }

    @Override
    public Users_dataadapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users_rowlayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Users_dataadapter.ViewHolder viewHolder, final int i) {

        ((ViewHolder) viewHolder).tv_name.setText(users.get(i).getName());
      // Picasso.with(context)
        ((ViewHolder) viewHolder).tv_android.setText("");
              // .load(android.get(i).get_image_url()).resize(240, 120).into(img_android);
        Picasso.with(context)
                .load(users.get(i).getImage())

               // .resize(120, 120)
       .transform(new CircleTransform())
                .into(((ViewHolder) viewHolder).img_android);
       // builder.build().load().into(img_android);
        ((ViewHolder) viewHolder).img_android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context,android.get(i).getAndroid_version_name(),Toast.LENGTH_LONG).show();
                Intent tt=new Intent(context,User_details.class);
                tt.putExtra("name",users.get(i).getName());
                tt.putExtra("imageurl",users.get(i).getImage());
                tt.putExtra("profileurl",users.get(i).geturl());


               tt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(tt);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_android;
        private TextView tv_name;
        private ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            tv_name = (TextView)view.findViewById(R.id.tv_name);
            img_android = (ImageView) view.findViewById(R.id.img_android);

        }
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

}