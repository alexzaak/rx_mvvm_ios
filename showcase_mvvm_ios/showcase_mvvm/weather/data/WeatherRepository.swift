//
//  WeatherRepository.swift
//  showcase_mvvm
//
//  Created by Zaak, Alexander on 21.06.19.
//  Copyright © 2019 Zaak, Alexander. All rights reserved.
//

import Foundation
import RxSwift
import RxRetroSwift

class WeatherRepository {
    lazy var apiClient = ApiClient.shared

    func fetchWeather(id:Int) -> Single<Result<[Weather], ErrorModel>> {
        return self.apiClient
            .fetchWeather(id: id)
            .subscribeOn(Schedulers.shared.backgroundWorkScheduler)
            .retry(3)
            .observeOn(CurrentThreadScheduler.instance)
            .asSingle()
    }

    func fetchImage(url: String) -> Single<Data> {
        return self.apiClient.fetchImage(url: url)
            .subscribeOn(Schedulers.shared.backgroundWorkScheduler)
            .retry(3)
            .observeOn(CurrentThreadScheduler.instance)
            .asSingle()
    }
}
