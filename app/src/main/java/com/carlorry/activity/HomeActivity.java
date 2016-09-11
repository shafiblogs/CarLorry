package com.carlorry.activity;

import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.carlorry.Utils.StringUtils;
import com.carlorry.views.DraggableFlowLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener{
    private DraggableFlowLayout homeFlowLayout;
    private int HOME_LAYOUT_WIDTH = 0;
    private int NO_OF_COLUMNS = 2;
    private double TILE_ASPECT_RATIO = 1.15;
    private int CARD_WIDTH = 0;

    private LayoutInflater layoutInflater;
    private myDragEventListener mDragListen;
    private View.OnLongClickListener longClickListner;

    private ArrayList<Integer> cardIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cardIds=new ArrayList<>();
        cardIds.add(1);
        cardIds.add(2);
        cardIds.add(3);
        cardIds.add(4);
        initializeViews();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initializeViews() {
        mDragListen = new myDragEventListener();
        layoutInflater = LayoutInflater.from(this);

        homeFlowLayout = (DraggableFlowLayout) findViewById(R.id.homeFlow);
        LayoutTransition l = new LayoutTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            l.enableTransitionType(LayoutTransition.CHANGING);
        }
        homeFlowLayout.setLayoutTransition(l);

        ViewTreeObserver vto = homeFlowLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        homeFlowLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    initializeLayout();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        longClickListner = new View.OnLongClickListener() {

            public boolean onLongClick(View v) {

                ClipData.Item item = new ClipData.Item((CharSequence) String.valueOf(homeFlowLayout.indexOfChild(v)));
                String[] mimes = {"text/plain"};
                ClipData dragData = new ClipData((CharSequence) String.valueOf(v.getId()), mimes, item);

                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);

                // Starts the drag
                return v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );
            }
        };
    }

    protected void initializeLayout() {
        int width = homeFlowLayout.getMeasuredWidth();
        int height = homeFlowLayout.getMeasuredHeight();
        HOME_LAYOUT_WIDTH = width;

        calculateMetrics();

        homeFlowLayout.removeAllViews();
        for (int cardId : cardIds) {
            View tempView = addHomeCard(R.layout.card_layout, cardId, false, true, false);
//            refreshFonts(tempView);
        }

    }

    private int calculateMetrics() {

        if (this.getWindowManager() == null) return 0;

        Display display = this.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        int realDeviceWidth = StringUtils.pxToDp(this, width);
        //dewaLog("Real Device Width:"+realDeviceWidth);
        if (realDeviceWidth >= 100 && realDeviceWidth <= 500)
            NO_OF_COLUMNS = 2;
        else if (realDeviceWidth > 500 && realDeviceWidth <= 800)
            NO_OF_COLUMNS = 3;
        else if (realDeviceWidth > 800 && realDeviceWidth <= 1200)
            NO_OF_COLUMNS = 4;
        else
            NO_OF_COLUMNS = 2;

        //NO_OF_COLUMNS = 5;

        CARD_WIDTH = HOME_LAYOUT_WIDTH / NO_OF_COLUMNS;

		/*dewaLog("HOME_LAYOUT_WIDTH:"+HOME_LAYOUT_WIDTH);
        dewaLog("Number of Columns: "+NO_OF_COLUMNS);
		dewaLog("CardView Width: "+(HOME_LAYOUT_WIDTH / NO_OF_COLUMNS));*/
        return CARD_WIDTH;

    }

    public View addHomeCard(int resLayoutId, int cardViewId, boolean isLong, boolean isDragable, boolean hasMenu) {
        View view = layoutInflater.inflate(resLayoutId, null);
        setupCardLay(view, cardViewId);

        int width = CARD_WIDTH;
        int height = (int) (CARD_WIDTH * TILE_ASPECT_RATIO);

        if (isLong)
            width = width * 2;

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, height);
        view.setLayoutParams(params);
        view.setId(cardViewId);
        view.setTag(isLong);
        view.setOnClickListener(this);

        CardView cardView = (CardView) view.findViewById(R.id.card_view);
        if (cardView != null) {
            cardView.setPreventCornerOverlap(false);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(this, R.anim.anim_card);
//                //cardView.setStateListAnimator(stateListAnimator);
//            }
        }

        if (isDragable) {
            view.setOnLongClickListener(longClickListner);
            view.setOnDragListener(mDragListen);
        }

        homeFlowLayout.addView(view);
        return view;
    }

    private void setupCardLay(View view, int cardViewId) {
//        TextView footer = (TextView) view.findViewById(R.id.tv_footer);
//        ImageView cardIm = (ImageView) view.findViewById(R.id.iv_card);

        switch (cardViewId) {
//            case Constants.CARD_ELECTRONICS_ID:
//                footer.setText("Electronics");
//                cardIm.setImageResource(R.drawable.card_electronics);
//                break;
//            case Constants.CARD_AUTOMOBILES_ID:
//                footer.setText("Automobiles");
//                cardIm.setImageResource(R.drawable.card_automobiles);
//                break;
//            case Constants.CARD_MOBILE_PHONES_PDA_ID:
//                footer.setText("Mobile phones & PDA");
//                cardIm.setImageResource(R.drawable.card_phones);
//                break;
//            case Constants.CARD_HOME_FURNITURE_ID:
//                footer.setText("Home & Furniture");
//                cardIm.setImageResource(R.drawable.card_furniture);
//                break;
//            case Constants.CARD_TOYS_ID:
//                footer.setText("Toys");
//                cardIm.setImageResource(R.drawable.card_toys);
//                break;
//            case Constants.CARD_JEWELLERY_WATCHES_ID:
//                footer.setText("Jewellery & Watches");
//                cardIm.setImageResource(R.drawable.card_jewellery);
//                break;
        }
    }

    @Override
    public void onClick(View view) {

    }

    public class myDragEventListener implements View.OnDragListener {

        // This is the method that the system calls when it dispatches a drag event to the
        // listener.
        public boolean onDrag(View v, DragEvent event) {

            // Defines a variable to store the action type for the incoming event
            final int action = event.getAction();

            CharSequence dragData;
            // Handles each of the expected events
            switch (action) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                        // As an example of what your application might do,
                        // applies a blue color tint to the View to indicate that it can accept
                        // data.
                        //((LinearLayout) v).setBackgroundColor(Color.BLUE);

                        // Invalidate the view to force a redraw in the new tint
                        v.invalidate();

                        // returns true to indicate that the View can accept the dragged data.
                        return true;

                    }

                    // Returns false. During the current drag and drop operation, this View will
                    // not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View. Return true; the return value is ignored.

                    //((LinearLayout) v).setBackgroundColor(Color.GREEN);

                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(ObjectAnimator.ofFloat(v, "alpha", 1, 0.5f));
                    set.setDuration(200).start();
                    //				((LinearLayout) v).setBackgroundColor(Color.GREEN);

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    AnimatorSet setExit = new AnimatorSet();
                    setExit.playTogether(ObjectAnimator.ofFloat(v, "alpha", 0.5f, 1));
                    setExit.setDuration(200).start();

                    // Re-sets the color tint to blue. Returns true; the return value is ignored.
                    //((LinearLayout) v).setBackgroundColor(Color.BLUE);

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    return true;

                case DragEvent.ACTION_DROP:

                    // Gets the item containing the dragged data
                    ClipData.Item item = event.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    dragData = item.getText();

                    // Displays a message containing the dragged data.
                    //HomeFragment.this.dewaLog("View " +dragData +" is dragged into "+homeFlowLayout.indexOfChild(v));

                    //swap views
                    View sourceView = homeFlowLayout.getChildAt(Integer.valueOf((String) dragData));
                    View destinationView = v;

                    int sourceIndex = homeFlowLayout.indexOfChild(sourceView);
                    int destinationIndex = homeFlowLayout.indexOfChild(destinationView);

                    if (sourceIndex != destinationIndex) {
                        homeFlowLayout.removeView(sourceView);
                        homeFlowLayout.removeView(destinationView);

                        if (sourceIndex < destinationIndex) {
                            homeFlowLayout.addView(destinationView, sourceIndex);
                            homeFlowLayout.addView(sourceView, destinationIndex);
                        } else {
                            homeFlowLayout.addView(sourceView, destinationIndex);
                            homeFlowLayout.addView(destinationView, sourceIndex);
                        }
                    }


                    // Turns off any color tints
                    //((LinearLayout) v).setBackgroundColor(color.transparent);
                    v.setBackgroundColor(Color.LTGRAY);

                    // Invalidates the view to force a redraw
                    v.invalidate();
                    //homeFlowLayout.requestFocus();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:

                    // Turns off any color tinting
                    //((LinearLayout) v).setBackgroundColor(color.transparent);
                    v.setBackgroundColor(Color.TRANSPARENT);


                    // Invalidates the view to force a redraw
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                /*if (event.getResult()) {
                    HomeActivity.this.dewaLog("The drop was handled.");

				} else {
					HomeActivity.this.dewaLog("The drop dint work!!.");

				}*/
                    homeFlowLayout.invalidate();
                    // returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                    break;
            }

            return false;
        }
    }

    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        // The drag shadow image, defined as a drawable thing
        private static Drawable shadow;

        // Defines the constructor for myDragShadowBuilder
        public MyDragShadowBuilder(View v) {

            // Stores the View parameter passed to myDragShadowBuilder.
            super(v);

			/*v.setDrawingCacheEnabled(true);
            v.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
			v.buildDrawingCache(true);
			Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
			v.setDrawingCacheEnabled(false); // clear drawing cache
			*/
            // Creates a draggable image that will fill the Canvas provided by the system.
            shadow = new ColorDrawable(Color.LTGRAY);
            //shadow = b;
            //shadow =  new BitmapDrawable(v.getContext().getResources(), b);
        }

        // Defines a callback that sends the drag shadow dimensions and touch point back to the
        // system.
        @Override
        public void onProvideShadowMetrics(Point size, Point touch) {
            // Defines local variables
            int width;
            int height;

            // Sets the width of the shadow to half the width of the original View
            width = getView().getWidth() / 2;

            // Sets the height of the shadow to half the height of the original View
            height = getView().getHeight() / 2;

            // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
            // Canvas that the system will provide. As a result, the drag shadow will fill the
            // Canvas.
            shadow.setBounds(0, 0, width, height);

            // Sets the size parameter's width and height values. These get back to the system
            // through the size parameter.
            size.set(width, height);

            // Sets the touch point's position to be in the middle of the drag shadow
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
        // from the dimensions passed in onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {
            // Draws the ColorDrawable in the Canvas passed in from the system.
            shadow.draw(canvas);
        }
    }
}
