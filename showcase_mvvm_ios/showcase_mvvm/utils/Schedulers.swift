//
//  Schedulers.swift
//  showcase_mvvm
//
//  Created by Zaak, Alexander on 05.07.19.
//  Copyright © 2019 Zaak, Alexander. All rights reserved.
//
import RxSwift

import class Foundation.URLSession
import class Foundation.OperationQueue
import enum Foundation.QualityOfService

class Schedulers {

    static let shared = Schedulers() // Singleton

    let backgroundWorkScheduler: ImmediateSchedulerType
    let mainScheduler: SerialDispatchQueueScheduler

    private init() {
        let operationQueue = OperationQueue()
        operationQueue.maxConcurrentOperationCount = 2
        operationQueue.qualityOfService = QualityOfService.userInitiated
        self.backgroundWorkScheduler = OperationQueueScheduler(operationQueue: operationQueue)

        self.mainScheduler = MainScheduler.instance
    }
}
