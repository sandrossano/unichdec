package com.example.sandro.dec_dipartimentoeconomia;

/**
 * Created by sandro on 25/04/18.
 */

        import android.content.Context;
        import android.support.v4.view.PagerAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.squareup.picasso.Picasso;

        import java.util.ArrayList;

        import static com.example.sandro.dec_dipartimentoeconomia.MainActivity.immagini_multidip;
        import static com.example.sandro.dec_dipartimentoeconomia.SplashActivity.immagini_dec;

public class SlideAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public SlideAdapter(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);
        Picasso.with(context).load("https://economia.unich.it/immagini_home/"+immagini_multidip.get(position).getPath()).into(myImage);
        TextView text=(TextView)myImageLayout.findViewById(R.id.textView10) ;
        text.setVisibility(View.VISIBLE);
        text.setText(immagini_multidip.get(position).getTitolo());
        if (immagini_multidip.get(position).getTitolo().equals("")){text.setVisibility(View.INVISIBLE);}
        //myImage.setImageResource(images.get(position));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
