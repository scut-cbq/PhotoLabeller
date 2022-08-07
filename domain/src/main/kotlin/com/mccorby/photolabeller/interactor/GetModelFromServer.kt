package com.mccorby.photolabeller.interactor

import com.mccorby.executors.ExecutionContext
import com.mccorby.fp.Either
import com.mccorby.photolabeller.model.Stats
import com.mccorby.photolabeller.model.Trainer
import com.mccorby.photolabeller.repository.FederatedDataSource

class GetModelFromServer(private val dataSource: FederatedDataSource,
                         private val trainer: Trainer,
                         executionContext: ExecutionContext,
                         postExecutionContext: ExecutionContext):
    UseCase<Stats, NoParams>(executionContext, postExecutionContext) {

    override suspend fun run(params: NoParams): Stats {
        val modelFile = dataSource.updateModel()
        return when (modelFile) {
            is Either.Right -> {
                trainer.loadModel(modelFile.value)
                Stats("Model updated")
            }
            is Either.Left -> {
                Stats("Server not available")
            }
        }
    }
}
