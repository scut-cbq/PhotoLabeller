package com.mccorby.photolabeller.labeller

import com.mccorby.photolabeller.interactor.*
import com.mccorby.photolabeller.repository.FederatedRepository
import java.io.File

class LabellingPresenter(private val repository: FederatedRepository,
                         private val getModel: GetModel,
                         private val predict: Predict,
                         private val labelImage: LabelImage,
                         private val getModelFromServer: GetModelFromServer) {

    private var view: LabellingView? = null


    fun saveLabelledImage(photoPath: String, label: String) = labelImage.execute({}, LabelImageParams(photoPath, label))

    fun loadModel() = getModel.execute({view?.onModelLoaded(it)}, NoParams())

    fun updateModel() = getModelFromServer.execute({view?.onGetModelFromServer(it)}, NoParams())

    fun createImageFile(): File = repository.createImage()

    fun predict(image: File) = predict.execute({view?.onPrediction(it)}, PredictParams(image))

    fun attach(view: LabellingView) { this.view = view }

    fun detach() { view = null }
}

