package com.varos.picmap.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifResourceEncoder;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapperResourceEncoder;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.plus.model.people.Person;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;

import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.varos.picmap.R;
import com.varos.picmap.models.BaseImageMarker;
import com.varos.picmap.models.ImageMarker;
import com.varos.picmap.models.MultiDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ClusterManager.OnClusterClickListener<ImageMarker>, ClusterManager.OnClusterInfoWindowClickListener<ImageMarker>, ClusterManager.OnClusterItemClickListener<ImageMarker>, ClusterManager.OnClusterItemInfoWindowClickListener<ImageMarker> {
/*
    private Random mRandom = new Random(1984);

    public ClusterManager<ImageMarker> mClusterManager;

    GoogleMap mMap;
    ArrayList paths=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        paths=getIntent().getStringArrayListExtra("data");
        setUpMap();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (mMap != null) {
            return;
        }
        mMap = googleMap;
        startDemo();



    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpMap();
    }

    protected GoogleMap getMap() {
        return mMap;
    }
    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    public class ImageRenderer extends DefaultClusterRenderer<ImageMarker>
    {



        private final IconGenerator mIconGenerator = new IconGenerator(getApplicationContext());
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mImageView;
        private final ImageView mClusterImageView;
        private final int mDimension;

        public ImageRenderer() {
            super(getApplicationContext(), getMap(), mClusterManager);

            View multiProfile = getLayoutInflater().inflate(R.layout.cluster, null);
            mClusterIconGenerator.setContentView(multiProfile);
            mClusterImageView = (ImageView) multiProfile.findViewById(R.id.image);

            mImageView = new ImageView(getApplicationContext());
            mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = (int) getResources().getDimension(R.dimen.custom_profile_padding);
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);
        }

        @Override
        protected void onBeforeClusterItemRendered(final ImageMarker imageMarker, MarkerOptions markerOptions) {
            // Draw a single person.
            // Set the info window to show their name.
            *//*GifBitmapWrapperResourceEncoder encoder =new GifBitmapWrapperResourceEncoder(new BitmapEncoder(Bitmap.CompressFormat.JPEG, 50), new GifResourceEncoder(Glide.get(getApplicationContext()).getBitmapPool()));
            // Draw a single person.
            // Set the info window to show their name.
            Glide.with(getApplicationContext()).load(imageMarker.getmImageUrl()).asBitmap().override(50,50).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESULT).into(mImageView);
            Bitmap icon = mIconGenerator.makeIcon();
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(imageMarker.mImageName);*//*
            Glide
                    .with(getApplicationContext())
                    .load(imageMarker.getmImageUrl())
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            mImageView.setImageDrawable(resource);
                            Bitmap icon = mIconGenerator.makeIcon();
                            Marker markerToChange = null;
                            for (Marker marker : mClusterManager.getMarkerCollection().getMarkers()) {
                                if (marker.getPosition().equals(imageMarker.getPosition())) {
                                    markerToChange = marker;
                                }
                            }
                            // if found - change icon
                            if (markerToChange != null) {
                                markerToChange.setIcon(BitmapDescriptorFactory.fromBitmap(icon));
                            }

                        }
                    });
           // Bitmap icon = mIconGenerator.makeIcon();
            //markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }

        @Override
        protected void onBeforeClusterRendered(final Cluster<ImageMarker> cluster, final MarkerOptions markerOptions) {
            // Draw multiple people.
            // Note: this method runs on the UI thread. Don't spend too much time in here (like in this example).
           *//* List<Drawable> profilePhotos = new ArrayList<Drawable>(Math.min(4, cluster.getSize()));
            int width = mDimension;
            int height = mDimension;

            for (ImageMarker p : cluster.getItems()) {
                // Draw 4 at most.
                if (profilePhotos.size() == 4) break;
                Drawable drawable = GlideBitmapDrawable.createFromPath(p.getmImageUrl());
                drawable.setBounds(0, 0, width, height);
                profilePhotos.add(drawable);
            }
            MultiDrawable multiDrawable = new MultiDrawable(profilePhotos);
            multiDrawable.setBounds(0, 0, width, height);

            mClusterImageView.setImageDrawable(multiDrawable);
            Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));*//*
            final List<Drawable> profilePhotos = new ArrayList<Drawable>(Math.min(4, cluster.getSize()));
            final int width = mDimension;
            final int height = mDimension;

            int i = 0;

            for (final ImageMarker p : cluster.getItems()) {
                // Draw 4 at most.
                i++;
                Glide
                        .with(getApplicationContext())
                        .load(p.getmImageUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                resource.setBounds(0, 0, width, height);
                                profilePhotos.add(resource);
                                MultiDrawable multiDrawable = new MultiDrawable(profilePhotos);
                                multiDrawable.setBounds(0, 0, width, height);

                                mClusterImageView.setImageDrawable(multiDrawable);
                                Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
                                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
                            }
                        });

                if (i == 4) break;
            }
        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
            // Always render clusters.
            return cluster.getSize() > 1;
        }


    *//*@Override
    public boolean onClusterClick(Cluster<ImageMarker> cluster) {
        // Show a toast with some info when the cluster is clicked.
        String firstName = cluster.getItems().iterator().next().getmMarkerName();
        Toast.makeText(this, cluster.getSize() + " (including " + firstName + ")", Toast.LENGTH_SHORT).show();

        // Zoom in the cluster. Need to create LatLngBounds and including all the cluster items
        // inside of bounds, then animate to center of the bounds.

        // Create the builder to collect all essential cluster items for the bounds.
        LatLngBounds.Builder builder = LatLngBounds.builder();
        for (ClusterItem item : cluster.getItems()) {
            builder.include(item.getPosition());
        }
        // Get the LatLngBounds
        final LatLngBounds bounds = builder.build();

        // Animate camera to the bounds
        try {
            getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<ImageMarker> cluster) {
        // Does nothing, but you could go to a list of the users.
    }

    @Override
    public boolean onClusterItemClick(ImageMarker item) {
        // Does nothing, but you could go into the user's profile page, for example.
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(ImageMarker item) {
        // Does nothing, but you could go into the user's profile page, for example.
    }*//*




    }
    public void startDemo() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 9.5f));

        mClusterManager = new ClusterManager<ImageMarker>(getApplicationContext(), getMap());
        mClusterManager.setRenderer(new ImageRenderer());
        getMap().setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                mClusterManager.cluster();
            }
        });
        getMap().setOnMarkerClickListener(mClusterManager);
        getMap().setOnInfoWindowClickListener(mClusterManager);
      *//*  mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);*//*

        addItems();
        mClusterManager.cluster();
    }

    private void addItems() {
        for(int i=0;i<10;i++)
        {

            mClusterManager.addItem(new ImageMarker(position(),paths.get(i).toString(),paths.get(i).toString()));
        }

    }
    private LatLng position() {
        return new LatLng(random(51.6723432, 51.38494009999999), random(0.148271, -0.3514683));
    }

    private double random(double min, double max) {
        return mRandom.nextDouble() * (max - min) + min;
    }*/

    ArrayList paths = new ArrayList();
    private GoogleMap mMap;
    private ClusterManager<ImageMarker> mClusterManager;
    private Random mRandom = new Random(1984);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        paths = getIntent().getStringArrayListExtra("data");
        setUpMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        startDemo();
    }

    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    protected int getLayoutId() {
        return R.layout.activity_maps;
    }

    protected GoogleMap getMap() {
        return mMap;
    }

    @Override
    public boolean onClusterClick(Cluster<ImageMarker> cluster) {
        // Show a toast with some info when the cluster is clicked.
        String firstName = cluster.getItems().iterator().next().getmMarkerName();
        Toast.makeText(this, cluster.getSize() + " (including " + firstName + ")", Toast.LENGTH_SHORT).show();

        // Zoom in the cluster. Need to create LatLngBounds and including all the cluster items
        // inside of bounds, then animate to center of the bounds.

        // Create the builder to collect all essential cluster items for the bounds.
        LatLngBounds.Builder builder = LatLngBounds.builder();
        for (ClusterItem item : cluster.getItems()) {
            builder.include(item.getPosition());
        }
        // Get the LatLngBounds
        final LatLngBounds bounds = builder.build();

        // Animate camera to the bounds
        try {
            getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<ImageMarker> cluster) {
        // Does nothing, but you could go to a list of the users.
    }

    @Override
    public boolean onClusterItemClick(ImageMarker item) {
        // Does nothing, but you could go into the user's profile page, for example.
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(ImageMarker item) {
        // Does nothing, but you could go into the user's profile page, for example.
    }

    public void startDemo() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 9.5f));

        mClusterManager = new ClusterManager<ImageMarker>(getApplicationContext(), getMap());
        mClusterManager.setRenderer(new ImageRenderer());
        getMap().setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                mClusterManager.cluster();
            }
        });
        getMap().setOnMarkerClickListener(mClusterManager);
        getMap().setOnInfoWindowClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);

        addItems();
        mClusterManager.cluster();
    }

    private void addItems() {
        for(int i=0;i<10;i++)
        {
            mClusterManager.addItem(new ImageMarker(position(),paths.get(i).toString(),paths.get(i).toString()));
        }

    }

    private LatLng position() {
        return new LatLng(random(51.6723432, 51.38494009999999), random(0.148271, -0.3514683));
    }
    /*protected void startDemo() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 9.5f));

        mClusterManager = new ClusterManager<ImageMarker>(this, getMap());
        mClusterManager.setRenderer(new PersonRenderer());
        getMap().setOnCameraIdleListener(mClusterManager);
        getMap().setOnMarkerClickListener(mClusterManager);
        getMap().setOnInfoWindowClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(MapsActivity.this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);

        addItems();
        mClusterManager.cluster();
    }

    private void addItems() {
        // http://www.flickr.com/photos/sdasmarchives/5036248203/
        mClusterManager.addItem(new Person(position(), "Walter", R.drawable.walter));

        // http://www.flickr.com/photos/usnationalarchives/4726917149/
        mClusterManager.addItem(new Person(position(), "Gran", R.drawable.gran));

        // http://www.flickr.com/photos/nypl/3111525394/
        mClusterManager.addItem(new Person(position(), "Ruth", R.drawable.ruth));

        // http://www.flickr.com/photos/smithsonian/2887433330/
        mClusterManager.addItem(new Person(position(), "Stefan", R.drawable.stefan));

        // http://www.flickr.com/photos/library_of_congress/2179915182/
        mClusterManager.addItem(new Person(position(), "Mechanic", R.drawable.mechanic));

        // http://www.flickr.com/photos/nationalmediamuseum/7893552556/
        mClusterManager.addItem(new Person(position(), "Yeats", R.drawable.yeats));

        // http://www.flickr.com/photos/sdasmarchives/5036231225/
        mClusterManager.addItem(new Person(position(), "John", R.drawable.john));

        // http://www.flickr.com/photos/anmm_thecommons/7694202096/
        mClusterManager.addItem(new Person(position(), "Trevor the Turtle", R.drawable.turtle));

        // http://www.flickr.com/photos/usnationalarchives/4726892651/
        mClusterManager.addItem(new Person(position(), "Teach", R.drawable.teacher));
    }*/

    private double random(double min, double max) {
        return mRandom.nextDouble() * (max - min) + min;
    }

    /**
     * Draws profile photos inside markers (using IconGenerator).
     * When there are multiple people in the cluster, draw multiple photos (using MultiDrawable).
     */
    private class ImageRenderer extends DefaultClusterRenderer<ImageMarker> {

        private final IconGenerator mIconGenerator = new IconGenerator(getApplicationContext());
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mImageView;
        private final ImageView mClusterImageView;
        private final int mDimension;
        private TextView count;

        public ImageRenderer() {
            super(getApplicationContext(), getMap(), mClusterManager);

            View multiProfile = getLayoutInflater().inflate(R.layout.cluster, null);
            mClusterIconGenerator.setContentView(multiProfile);
            mClusterImageView = (ImageView) multiProfile.findViewById(R.id.icon_image);
            count = (TextView)multiProfile.findViewById(R.id.icon_count);

                mImageView = new ImageView(getApplicationContext());
                mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
                mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
                int padding = (int) getResources().getDimension(R.dimen.custom_profile_padding);
                mImageView.setPadding(padding, padding, padding, padding);
                mIconGenerator.setContentView(mImageView);
            }
          /*  super(getApplicationContext(), getMap(), mClusterManager);

            View multiProfile = getLayoutInflater().inflate(R.layout.multi_profile, null);
            mClusterIconGenerator.setContentView(multiProfile);
            mClusterImageView = (ImageView) multiProfile.findViewById(R.id.image);

            mImageView = new ImageView(getApplicationContext());
            mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = (int) getResources().getDimension(R.dimen.custom_profile_padding);
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);*/


        @Override
        protected void onBeforeClusterItemRendered(final ImageMarker imageMarker, MarkerOptions markerOptions) {
            // Draw a single person.
            // Set the info window to show their name.
            /*GifBitmapWrapperResourceEncoder encoder =new GifBitmapWrapperResourceEncoder(new BitmapEncoder(Bitmap.CompressFormat.JPEG, 50), new GifResourceEncoder(Glide.get(getApplicationContext()).getBitmapPool()));
            // Draw a single person.
            // Set the info window to show their name.
            Glide.with(getApplicationContext()).load(imageMarker.getmImageUrl()).asBitmap().override(50,50).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESULT).into(mImageView);
            Bitmap icon = mIconGenerator.makeIcon();
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(imageMarker.mImageName);*/
            Glide
                    .with(getApplicationContext())
                    .load(imageMarker.getmImageUrl())
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            mImageView.setImageDrawable(resource);
                            Bitmap icon = mIconGenerator.makeIcon();
                            Marker markerToChange = null;
                            for (Marker marker : mClusterManager.getMarkerCollection().getMarkers()) {
                                if (marker.getPosition().equals(imageMarker.getPosition())) {
                                    markerToChange = marker;
                                }
                            }
                            // if found - change icon
                            if (markerToChange != null) {
                                markerToChange.setIcon(BitmapDescriptorFactory.fromBitmap(icon));
                            }

                        }
                    });
            // Bitmap icon = mIconGenerator.makeIcon();
            //markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));

            //mImageView.setImageResource(person.profilePhoto);
            //Bitmap icon = mIconGenerator.makeIcon();
        //    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(marker.getmMarkerName());
        }

        @Override
        protected void onBeforeClusterRendered(final Cluster<ImageMarker> cluster, final MarkerOptions markerOptions) {
            // Draw multiple people.
            // Note: this method runs on the UI thread. Don't spend too much time in here (like in this example).
           /* List<Drawable> profilePhotos = new ArrayList<Drawable>(Math.min(4, cluster.getSize()));
            int width = mDimension;
            int height = mDimension;

            for (ImageMarker p : cluster.getItems()) {
                // Draw 4 at most.
                if (profilePhotos.size() == 4) break;
                Drawable drawable = GlideBitmapDrawable.createFromPath(p.getmImageUrl());
                drawable.setBounds(0, 0, width, height);
                profilePhotos.add(drawable);
            }
            MultiDrawable multiDrawable = new MultiDrawable(profilePhotos);
            multiDrawable.setBounds(0, 0, width, height);

            mClusterImageView.setImageDrawable(multiDrawable);
            Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));*/
            final List<Drawable> profilePhotos = new ArrayList<Drawable>(Math.min(4, cluster.getSize()));
            final int width = mDimension;
            final int height = mDimension;

            int i = 0;

            for (final ImageMarker p : cluster.getItems()) {
                // Draw 4 at most.
                i++;
                Glide
                        .with(getApplicationContext())
                        .load(p.getmImageUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                resource.setBounds(0, 0, width, height);
                                profilePhotos.add(resource);
                                MultiDrawable multiDrawable = new MultiDrawable(profilePhotos);
                                multiDrawable.setBounds(0, 0, width, height);

                                mClusterImageView.setImageDrawable(multiDrawable);
                                count.setText(String.valueOf(cluster.getSize()));
                               // Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
                               // markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
                            }
                        });

                if (i == 4) break;
            }
        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
            // Always render clusters.
            return cluster.getSize() > 1;
        }
    }
}




