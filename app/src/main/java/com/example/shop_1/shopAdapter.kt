package com.example.shop_1

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.math.RoundingMode

class shopAdapter(private val context: Context,
                  private val dataSource: ArrayList<Shop>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater



    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_shop, parent, false)



        val shop = getItem(position) as Shop

        // Get thumbnail element
        val thumbnailImageView = rowView.findViewById(R.id.imageView) as ImageView

        Picasso.get().load(shop.image).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

        // Get title element
        val nameTextView = rowView.findViewById(R.id.shop_list_name) as TextView


        // Get subtitle element
        val priceTextView = rowView.findViewById(R.id.shop_list_price) as TextView

        // Get detail element
        val stockTextView = rowView.findViewById(R.id.shop_list_stock) as TextView

        val pPrice_dec = BigDecimal(shop.price).setScale(2, RoundingMode.HALF_EVEN)


        nameTextView.text = shop.name
        priceTextView.text = "$" + pPrice_dec

        if (shop.stock.toInt() != 0) {
            stockTextView.text = shop.stock


        } else {
            stockTextView.text = "Out of Stock"
        }


        val btn_buy = rowView.findViewById(R.id.button_buy) as Button

        btn_buy.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(this.context)

            dialogBuilder.setMessage("Buy now ?")
                .setCancelable(false)
                .setPositiveButton("Yes", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()

                    btn_buy.text = "View Now"
                    true

                })
                .setNegativeButton("No", DialogInterface.OnClickListener{
                        dialog, id ->  dialog.cancel()
                })

            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("AlertDialogExample")
            // show alert dialog
            alert.show()

        }


        return rowView
    }



}