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

    func fetchWeather(id:Int) -> Single<Result<WeatherResult, ErrorModel>> {
        return self.apiClient
            .fetchWeather(id: id)
            .subscribeOn(CurrentThreadScheduler.instance)
            .retry(3)
            .observeOn(MainScheduler.instance)
            .asSingle()
    }

    func fetchImage(url: String) -> Single<Data> {
        return self.apiClient.fetchImage(url: url)
            .subscribeOn(CurrentThreadScheduler.instance)
            .retry(3)
            .observeOn(MainScheduler.instance)
            .asSingle()
    }
}
