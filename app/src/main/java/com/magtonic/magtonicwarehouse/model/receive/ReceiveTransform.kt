package com.magtonic.magtonicwarehouse.model.receive

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import org.json.XML


class ReceiveTransform {

   /*inner class RJReceiptUploadList {
        var dataList = ArrayList<RJReceiptUpload>()
    }

    inner class RJStorageUploadList {
        var dataList = ArrayList<RJStorageUpload>()
    }*/

    inner class RJMaterialList {
        var dataList = ArrayList<RJMaterial>()
    }

    inner class RJPropertyList {
        var dataList = ArrayList<RJProperty>()
    }

    inner class RJGuestList {
        var dataList = ArrayList<RJGuest>()
    }

    companion object {
        private val mTAG = ReceiveTransform::class.java.name
        //const val arrField : String = "dataList"
        fun addToJsonArrayStr(str: String): String {

            return "{\"dataList\":$str}"
        }

        fun restoreToJsonStr(str: String): String {
            /*var str = str

            //return  "{dataList:"+str + "}";
            str = str.substring(1, str.length - 1)
            return str*/
            //val jsonStr = str.substring(1, str.length - 1)
            return str.substring(1, str.length - 1)

        }

        fun restoreToJsonStr2(str: String): String {
            /*var str = str

            //return  "{dataList:"+str + "}";
            str = str.substring(1, str.length - 1)
            return str*/
            //val jsonStr = str.substring(1, str.length - 1)
            return str.substring(0, str.length)

        }

        fun restoreXmlToJson(str: String): String {
            Log.e(mTAG, "=== restoreXmlToJson start ===")

            val json: JSONObject = XML.toJSONObject(str)
            Log.d(mTAG, "json = $json")

            val resArray: JSONObject = json.getJSONObject("string")
            Log.d(mTAG, "resArray = $resArray")

            Log.e(mTAG, "string = ${resArray.getString("content")}")

            Log.e(mTAG, "=== restoreXmlToJson end ===")
            return "{\"dataList\":${resArray.getString("content")}}"
        }
    }


}