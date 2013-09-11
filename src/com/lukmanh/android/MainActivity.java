package com.lukmanh.android;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ListView mDrawerListRight;
	private ActionBarDrawerToggle mDrawerToggle;
	
	public static final int HDR_POS1 = 0;
	public static final int HDR_POS2 = 1;
	public static final int HDR_POS3 = 4;
	public static final int HDR_RIGHT = 0;
	public static final int HDR_RIGHT2 = 1;
	
	public static final String[] mHeader = {null,
						"Transaksi",null,null,
						"Laporan",null,null};
	
	public static final String[] mItem = {"Dashboard", 
						null,"Input Tagihan","Top Up", 
						null,"Cek Saldo","History Transaksi"};
	
	public static final int[] mIcon = {R.drawable.dashboard,
					0, R.drawable.transaction, R.drawable.transaction,
					0, R.drawable.report, R.drawable.report};
	
	
	public static final String[]mHeaderRight	= {"Account",null,null};
	public static final String[]mItemRight		= {null,"Info User","Logout"};
	public static final int[] mIconRight		= {0, R.drawable.account, R.drawable.account};
	
	private static final Integer LIST_HEADER = 0;
    private static final Integer LIST_ITEM = 1;
    
    private static final Integer LIST_HEADER_RIGHT = 0;
    private static final Integer LIST_ITEM_RIGHT = 1;
	      
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView lv = (ListView)findViewById(R.id.listView1);
        lv.setAdapter(new ListAdapterLeft(this));
        
        ListView lv2 = (ListView)findViewById(R.id.listView2);
        lv2.setAdapter(new ListAdapterRight(this));
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.listView1);
		mDrawerListRight = (ListView) findViewById(R.id.listView2);
		
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		mDrawerListRight.setOnItemClickListener(new DrawerItemClickListenerRight());
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		getActionBar().setSubtitle("Rp. 50");
		
		mDrawerToggle = new ActionBarDrawerToggle(this,
				mDrawerLayout,
				R.drawable.ic_drawer,
				R.string.drawer_open, 
				R.string.drawer_close
				) ;
				mDrawerLayout.setDrawerListener(mDrawerToggle);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private class DrawerItemClickListener implements
	ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
			view.setActivated(false);
		}
	}
	
	private class DrawerItemClickListenerRight implements
	ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItemRight(position);
			view.setActivated(false);
		}
	}
	
	private void selectItem(int position) {
		//update the main content by replacing fragments
		
		
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		
		switch (position) {
		
			case 0:
				getActionBar().setTitle(R.string.labelDashboard);
				break;
				
			case 2:
				getActionBar().setTitle(R.string.labelTagihan);
				break;
				
			case 3:
				getActionBar().setTitle(R.string.labelTopup);
				break;
				
			case 5:
				getActionBar().setTitle(R.string.labelCeksaldo);
				break;
			
			case 6:
				getActionBar().setTitle(R.string.labelHistory);
				break;
				
		}
		
		
		
		
		fragmentTransaction.commit();
		mDrawerList.setItemChecked(position, true);
		
//		setTitle(mPlanetTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}
	
	private void selectItemRight(int position) {
		//update the main content by replacing fragments
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		
		switch (position) {
			
			case 1:
				getActionBar().setTitle(R.string.labelInfo);
				break;
			
			case 2:
				getActionBar().setTitle(R.string.labelLogout);
				break;
		
		}
		
		fragmentTransaction.commit();
		mDrawerListRight.setItemChecked(position, true);
//		setTitle(mPlanetTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerListRight);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	private class ListAdapterLeft extends BaseAdapter {
		public ListAdapterLeft(Context context) {
            mContext = context;
        }
		
		@Override
		public int getCount() {
			return mHeader.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			String headerText = getHeader(position);
            if(headerText != null) {

                View item = convertView;
                if(convertView == null || convertView.getTag() == LIST_ITEM) {

                    item = LayoutInflater.from(mContext).inflate(
                            R.layout.drawer_header, parent, false);
                    item.setTag(LIST_HEADER);

                }

                TextView headerTextView = (TextView)item.findViewById(R.id.list_header);
                headerTextView.setText(headerText);
                return item;
            }

            View item = convertView;
            if(convertView == null || convertView.getTag() == LIST_HEADER) {
                item = LayoutInflater.from(mContext).inflate(
                        R.layout.drawer_menu, parent, false);
                item.setTag(LIST_ITEM);
            }

            TextView subtext = (TextView)item.findViewById(R.id.list_item);
            subtext.setText(mItem[position % mItem.length]);
            
            ImageView image = (ImageView)item.findViewById(R.id.icon);
            image.setImageResource(mIcon[position % mIcon.length]);
            
            //Set last divider in a sublist invisible
            View divider = item.findViewById(R.id.item_separator);
            if(position == HDR_POS2 -1) {
                divider.setVisibility(View.INVISIBLE);
            }


            return item;
		}
		
		private String getHeader(int position) {

            if(position == HDR_POS1 || position == HDR_POS2 || position == HDR_POS3) {
                return mHeader[position];
            }
            return null;
        }
		private final Context mContext;
	}
	
	private class ListAdapterRight extends BaseAdapter {
		public ListAdapterRight(Context context) {
            mContext = context;
        }
		
		@Override
		public int getCount() {
			return mHeaderRight.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			String headerText = getHeader(position);
            if(headerText != null) {

                View item = convertView;
                if(convertView == null || convertView.getTag() == LIST_ITEM_RIGHT) {

                    item = LayoutInflater.from(mContext).inflate(
                            R.layout.drawer_header, parent, false);
                    item.setTag(LIST_HEADER_RIGHT);

                }

                TextView headerTextView = (TextView)item.findViewById(R.id.list_header);
                headerTextView.setText(headerText);
                return item;
            }

            View item = convertView;
            if(convertView == null || convertView.getTag() == LIST_HEADER_RIGHT) {
                item = LayoutInflater.from(mContext).inflate(
                        R.layout.drawer_menu, parent, false);
                item.setTag(LIST_ITEM_RIGHT);
            }

            TextView subtext = (TextView)item.findViewById(R.id.list_item);
            subtext.setText(mItemRight[position % mItemRight.length]);
            
            ImageView image = (ImageView)item.findViewById(R.id.icon);
            image.setImageResource(mIconRight[position % mIconRight.length]);
            
            //Set last divider in a sublist invisible
            View divider = item.findViewById(R.id.item_separator);
            if(position == HDR_RIGHT2 -1) {
                divider.setVisibility(View.INVISIBLE);
            }


            return item;
		}
		
		private String getHeader(int position) {

            if(position == HDR_RIGHT) {
                return mHeaderRight[position];
            }
            return null;
        }
		private final Context mContext;
	}
}
