package com.whitessmoke.melisearcher.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.whitessmoke.melisearcher.adapters.detail.DetailAttributeAdapter
import com.whitessmoke.melisearcher.adapters.detail.DetailImgAdapter
import com.whitessmoke.melisearcher.adapters.detail.DetailVariationsAdapter
import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.databinding.ActivityDetailViewBinding
import com.whitessmoke.melisearcher.ext.isNull
import com.whitessmoke.melisearcher.ext.toPrice
import com.whitessmoke.melisearcher.ui.viewModels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailView : AppCompatActivity() {

    lateinit var binding: ActivityDetailViewBinding
    val viewModel: DetailViewModel by viewModels()

    lateinit var adapterImg: DetailImgAdapter
    lateinit var adapterVariations: DetailVariationsAdapter
    lateinit var adapterAttributes: DetailAttributeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.getDetailsProduct(intent.getStringExtra("id").toString())
        observers()
        listeners()
        createViewPager()
        createRvVariations()
        createRvAttributes()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Listeners de la vista
     */
    private fun listeners() {

        binding.vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.indicatorVP.text = "${(position + 1)}/${adapterImg.pictures.size}"
            }
        })
    }

    /**
     * Creamos el adapter para la lista de atributos/caracteristicas de un producto
     */
    private fun createRvAttributes() {
        adapterAttributes = DetailAttributeAdapter(emptyList())
        binding.rvInfoProduct.adapter = adapterAttributes
        binding.rvInfoProduct.layoutManager = LinearLayoutManager(this)
    }

    /**
     * Creamos el adapter para la lista de variaciones de un producto
     */
    private fun createRvVariations() {
        adapterVariations = DetailVariationsAdapter(emptyList())
        binding.rvVariations.adapter = adapterVariations
        binding.rvVariations.layoutManager = LinearLayoutManager(this)
    }

    /**
     * Creamos el adapter para el view pager de imagenes
     */
    private fun createViewPager() {
        adapterImg = DetailImgAdapter(emptyList())
        binding.vp.clipToPadding = false
        binding.vp.clipChildren = false
        binding.vp.offscreenPageLimit = 3
        binding.vp.getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
        binding.vp.adapter = adapterImg

    }

    /**
     * Observers que interactuan con la vista desde ResultsViewModel
     */
    private fun observers() {
        viewModel.errors.observe(this, {
            binding.tv.isVisible = true
            binding.tv.text = it
        })
        viewModel.loading.observe(this, {
            binding.loading.isVisible = it
        })

        viewModel.product.observe(this, {
            it?.let {
                bindData(it)
            }
        })
    }

    /**
     * Seteamos los datos en la vista de detalles
     * @param it Modelo del producto con todos los datos necesarios para mostrar
     */
    private fun bindData(it: ModelProduct) {
        binding.containerParent.isVisible = true
        binding.condition.text = if (it.condition.equals("new")) "Nuevo" else it.condition
        binding.sold.text = "${it.sold} vendidos"
        binding.title.text = it.title
        adapterImg.setItems(it.pictures)
        if (!it.variations.isNullOrEmpty()) {
            binding.rvVariations.isVisible = true
            adapterVariations.setItems(it.variations)
        }
        binding.price.text = it.price.toPrice()
        binding.inStock.text = "${it.stock} unidades disponibles"
        binding.locationValue.text =
            "${it.sellerAddess.city.name}, ${it.sellerAddess.state.name}, ${it.sellerAddess.country.name}"
        if (!it.attributes.isNullOrEmpty()) {
            binding.containerAttributes.isVisible = true
            binding.sep1.isVisible = true
            adapterAttributes.setItems(it.attributes)
        }
        if (!it.description.isNull() && !it.description.description.isNullOrEmpty()) {
            binding.containerDescription.isVisible = true
            binding.sep2.isVisible = true
            binding.descriptionVal.text = it.description.description
        }
        if (!it.warranty.isNullOrEmpty()) {
            binding.containerWarranty.isVisible = true
            binding.warrantyVal.text = it.warranty

        }
        binding.indicatorVP.text = "1/${adapterImg.pictures.size}"
    }
}