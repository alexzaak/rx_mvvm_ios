//
//  LocationViewModel.swift
//  showcase_mvvm
//
//  Created by Zaak, Alexander on 20.06.19.
//  Copyright © 2019 Zaak, Alexander. All rights reserved.
//

import Foundation
import RxSwift

class LocationViewModel {
    lazy var searchRepository = LocationRepository()
    var locationSubject: PublishSubject<[Location]> = PublishSubject.init()
    var errorSubject: PublishSubject<String> = PublishSubject.init()
    
    private let disposeBag = DisposeBag()
    
    
    func getNearlyLocations() {
        searchRepository
            .fetchLocations()
            .subscribe(onSuccess: {
                result in
                if let locations = result.value {
                    self.locationSubject.onNext(locations)
                }
                if let error = result.error {
                    self.errorSubject.onNext(error.errorDetail ?? "unknown")
                }
            },onError: {
                error in
                self.errorSubject.onNext(error.localizedDescription) }
            ).disposed(by: disposeBag)
    }
}
