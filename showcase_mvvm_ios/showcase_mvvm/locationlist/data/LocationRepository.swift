//
//  SearchRepository.swift
//  showcase_mvvm
//
//  Created by Zaak, Alexander on 20.06.19.
//  Copyright © 2019 Zaak, Alexander. All rights reserved.
//

import Foundation
import RxSwift
import RxRetroSwift

class LocationRepository {
    lazy var apiClient = ApiClient.shared

    func fetchLocations() -> Single<Result<[Location], ErrorModel>> {
        return self.apiClient
            .fetchLocations()
            .subscribeOn(CurrentThreadScheduler.instance)
            .retry(3)
            .observeOn(MainScheduler.instance)
            .asSingle()
    }

}
