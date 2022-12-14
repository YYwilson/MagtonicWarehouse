package com.magtonic.magtonicwarehouse.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

import com.magtonic.magtonicwarehouse.R
import com.magtonic.magtonicwarehouse.data.Constants
import com.magtonic.magtonicwarehouse.data.HomeGridItem
import com.magtonic.magtonicwarehouse.data.HomeGridItemAdapter

class HomeGridFragment : Fragment() {
    private val mTAG = HomeGridFragment::class.java.name

    private var homeGridContext: Context? = null
    private var appList = ArrayList<HomeGridItem>()

    private var homeGridItemAdapter: HomeGridItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(mTAG, "onCreate")

        homeGridContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(mTAG, "onCreateView")

        val view = inflater.inflate(R.layout.fragment_home_grid, container, false)

        val item0 = HomeGridItem("Receipt", R.drawable.baseline_receipt_black_48, R.string.nav_receipt)
        appList.add(item0)

        val item1 = HomeGridItem("Storage", R.drawable.factory_stock_house, R.string.nav_storage)
        appList.add(item1)

        val item2 = HomeGridItem("Material", R.drawable.baseline_unarchive_black_48, R.string.nav_material_issuing)
        appList.add(item2)

        val item3 = HomeGridItem("Property", R.drawable.baseline_monetization_on_black_48, R.string.nav_property)
        appList.add(item3)

        val item4 = HomeGridItem("PrintTest", R.drawable.baseline_print_black_48, R.string.nav_printer)
        appList.add(item4)

        val item5 = HomeGridItem("Logout", R.drawable.baseline_exit_to_app_black_48, R.string.nav_logout)
        appList.add(item5)

        val item6 = HomeGridItem("Setting", R.drawable.baseline_settings_black_48, R.string.nav_setting)
        appList.add(item6)

        val item7 = HomeGridItem("Supplier", R.drawable.baseline_transfer_within_a_station_black_48, R.string.nav_guest)
        appList.add(item7)

        val item8 = HomeGridItem("About", R.drawable.baseline_info_black_48, R.string.nav_about)
        appList.add(item8)

        val item9 = HomeGridItem("Paint", R.drawable.baseline_edit_black_48, R.string.nav_paint)
        appList.add(item9)




        val gridView = view.findViewById<GridView>(R.id.gridViewHome)

        homeGridItemAdapter = HomeGridItemAdapter(homeGridContext, R.layout.fragment_home_grid_item, appList)
        //listView.setAdapter(receiptDetailItemAdapter)
        gridView!!.adapter = homeGridItemAdapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Log.d(mTAG, "click $position")

            //val appId = appList[position].getAppId()

            when(appList[position].getAppId()) {
                "Receipt" -> {
                    val showIntent = Intent()
                    showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_RECEIPT_ACTION
                    homeGridContext!!.sendBroadcast(showIntent)
                }
                "Storage" -> {
                    val showIntent = Intent()
                    showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_STORAGE_ACTION
                    homeGridContext!!.sendBroadcast(showIntent)
                }
                "Material" -> {
                    val showIntent = Intent()
                    showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_MATERIAL_ACTION
                    homeGridContext!!.sendBroadcast(showIntent)
                }
                "Property" -> {
                    val showIntent = Intent()
                    showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_PROPERTY_ACTION
                    homeGridContext!!.sendBroadcast(showIntent)
                }
                "PrintTest" -> {
                    val showIntent = Intent()
                    showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_TAG_PRINTER_ACTION
                    homeGridContext!!.sendBroadcast(showIntent)
                }
                "Logout" -> {
                    val showIntent = Intent()
                    showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_LOGOUT_ACTION
                    homeGridContext!!.sendBroadcast(showIntent)
                }
                "Setting" -> {
                    val showIntent = Intent()
                    showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_USER_SETTING_ACTION
                    homeGridContext!!.sendBroadcast(showIntent)
                }
                "Supplier" -> {
                    val showIntent = Intent()
                    showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_GUEST_ACTION
                    homeGridContext!!.sendBroadcast(showIntent)
                }
                "About" -> {
                    val showIntent = Intent()
                    showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_ABOUT_ACTION
                    homeGridContext!!.sendBroadcast(showIntent)
                }
                "Paint" -> {
                    val showIntent = Intent()
                    showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_PAINT_ACTION
                    homeGridContext!!.sendBroadcast(showIntent)
                }
            }


        }
        /*val linearLayoutLine1 = view.findViewById<LinearLayout>(R.id.line1)
        val linearLayoutLine2 = view.findViewById<LinearLayout>(R.id.line2)
        val linearLayoutLine3 = view.findViewById<LinearLayout>(R.id.line3)

        val imageViewReceipt = view.findViewById<ImageView>(R.id.imageViewReceipt)
        val textViewReceipt = view.findViewById<TextView>(R.id.textViewReceipt)
        val imageViewStorage = view.findViewById<ImageView>(R.id.imageViewStorage)
        val textViewStorage = view.findViewById<TextView>(R.id.textViewStorage)
        val imageViewMaterial = view.findViewById<ImageView>(R.id.imageViewMaterial)
        val textViewMaterial = view.findViewById<TextView>(R.id.textViewMaterial)
        val imageViewProperty = view.findViewById<ImageView>(R.id.imageViewProperty)
        val textViewProperty = view.findViewById<TextView>(R.id.textViewProperty)
        val imageViewTagPrinter = view.findViewById<ImageView>(R.id.imageViewTagPrinter)
        val textViewTagPrinter = view.findViewById<TextView>(R.id.textViewTagPrinter)
        val imageViewLogout = view.findViewById<ImageView>(R.id.imageViewLogout)
        val textViewLogout = view.findViewById<TextView>(R.id.textViewLogout)
        val imageViewAbout = view.findViewById<ImageView>(R.id.imageViewAbout)
        val textViewAbout = view.findViewById<TextView>(R.id.textViewAbout)
        val imageViewSetting = view.findViewById<ImageView>(R.id.imageViewSetting)
        val textViewSetting = view.findViewById<TextView>(R.id.textViewSetting)
        val imageViewGuest = view.findViewById<ImageView>(R.id.imageViewGuest)
        val textViewGuest = view.findViewById<TextView>(R.id.textViewGuest)

        if (MainActivity.isSecurityGuard) {
            linearLayoutLine1.visibility = View.GONE
            linearLayoutLine2.visibility = View.GONE
            linearLayoutLine3.visibility = View.VISIBLE
        }

        imageViewReceipt.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_RECEIPT_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        textViewReceipt.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_RECEIPT_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        imageViewStorage.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_STORAGE_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        textViewStorage.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_STORAGE_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        imageViewMaterial.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_MATERIAL_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        textViewMaterial.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_MATERIAL_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        imageViewProperty.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_PROPERTY_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        textViewProperty.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_PROPERTY_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        imageViewTagPrinter.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_TAG_PRINTER_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        textViewTagPrinter.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_TAG_PRINTER_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        imageViewLogout.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_LOGOUT_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        textViewLogout.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_LOGOUT_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        imageViewAbout.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_ABOUT_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        textViewAbout.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_ABOUT_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        imageViewSetting.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_USER_SETTING_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        textViewSetting.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_USER_SETTING_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        imageViewGuest.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_GUEST_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }

        textViewGuest.setOnClickListener {
            val showIntent = Intent()
            showIntent.action = Constants.ACTION.ACTION_HOME_GO_TO_GUEST_ACTION
            homeGridContext!!.sendBroadcast(showIntent)
        }*/

        return view
    }

    override fun onDestroyView() {
        Log.i(mTAG, "onDestroy")





        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i(mTAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)

    }
}