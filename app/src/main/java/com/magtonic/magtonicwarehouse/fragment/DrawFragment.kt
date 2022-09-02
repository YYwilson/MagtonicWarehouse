package com.magtonic.magtonicwarehouse.fragment

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import androidx.fragment.app.Fragment
import com.magtonic.magtonicwarehouse.R

import com.magtonic.magtonicwarehouse.data.PaintBoard


import java.io.IOException
import java.io.OutputStream



class DrawFragment : Fragment() {
    private val mTAG = DrawFragment::class.java.name
    private var drawContext: Context? = null


    private var paintBoard: PaintBoard?= null
    private var btnClear: Button?= null
    private var btnSave: Button?=null
    private var btnPrev: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drawContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(mTAG, "onCreateView")



        val view = inflater.inflate(R.layout.fragment_draw, container, false)
        paintBoard = view.findViewById(R.id.viewPaint)
        btnClear = view.findViewById(R.id.btnClear)
        btnSave = view.findViewById(R.id.btnSave)
        btnPrev = view.findViewById(R.id.btnPrev)

        btnClear!!.setOnClickListener {
            paintBoard!!.clear()
        }

        btnSave!!.setOnClickListener {
            showLogoutConfirmDialog()
        }

        btnPrev!!.setOnClickListener {
            paintBoard!!.undo()
        }


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

    /*fun saveBitmap(stream: OutputStream) {
        paintBoard!!.bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    }*/

    /*fun save() {
        try {
            val fileName = (System.currentTimeMillis() / 1000).toString() + ".jpg"
            val file = File(getExternalFilesDir)
            //val file = File(Environment.getExternalStorageDirectory(), fileName)
            val stream = FileOutputStream(file)
            saveBitmap(stream)
            stream.close()

            val intent = Intent()
            intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()))
            sendBroadcast(intent)

            Log.e(mTAG, "Save Success")

        } catch(e:Exception) {
            println(e)
            Log.e(mTAG, "Save Failed")

        }
    }*/

    @Throws(IOException::class)
    private fun saveBitmap(
        context: Context, bitmap: Bitmap,
        format: CompressFormat, mimeType: String,
        displayName: String
    ) {
        val relativeLocation = Environment.DIRECTORY_PICTURES
        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, relativeLocation)
        }
        val resolver = context.contentResolver
        var stream: OutputStream? = null
        var uri: Uri? = null
        try {
            val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            uri = resolver.insert(contentUri, contentValues)
            if (uri == null) {
                throw IOException("Failed to create new MediaStore record.")
            }
            stream = resolver.openOutputStream(uri)
            if (stream == null) {
                throw IOException("Failed to get output stream.")
            }
            val ret =bitmap.compress(format, 100, stream)
            if (!ret) {
                throw IOException("Failed to save bitmap.")
            }
        } catch (e: IOException) {
            if (uri != null) {
                resolver.delete(uri, null, null)
            }
            throw e
        } finally {
            stream?.close()
        }
    }

    private fun showLogoutConfirmDialog() {

        // get prompts.xml view
        /*LayoutInflater layoutInflater = LayoutInflater.from(Nfc_read_app.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);*/
        val promptView = View.inflate(drawContext, R.layout.confirm_dialog, null)

        val alertDialogBuilder = AlertDialog.Builder(drawContext).create()
        alertDialogBuilder.setView(promptView)

        //final EditText editFileName = (EditText) promptView.findViewById(R.id.editFileName);
        val textViewMsg = promptView.findViewById<TextView>(R.id.textViewDialog)
        val btnCancel = promptView.findViewById<Button>(R.id.btnDialogCancel)
        val btnConfirm = promptView.findViewById<Button>(R.id.btnDialogConfirm)


        textViewMsg.text = getString(R.string.draw_save_title)
        btnCancel.text = getString(R.string.cancel)
        btnConfirm.text = getString(R.string.confirm)


        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
        btnCancel!!.setOnClickListener {

            alertDialogBuilder.dismiss()
        }
        btnConfirm!!.setOnClickListener {
            val fileName = (System.currentTimeMillis() / 1000).toString() + ".jpg"
            saveBitmap(drawContext as Context, paintBoard!!.bitmap, CompressFormat.JPEG,"image/jpeg", fileName)
            alertDialogBuilder.dismiss()
        }
        alertDialogBuilder.show()


    }
}




