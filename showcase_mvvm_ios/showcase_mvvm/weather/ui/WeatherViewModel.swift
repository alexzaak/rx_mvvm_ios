//
//  WeatherViewModel.swift
//  showcase_mvvm
//
//  Created by Zaak, Alexander on 21.06.19.
//  Copyright © 2019 Zaak, Alexander. All rights reserved.
//

import Foundation
import RxSwift

class WeatherViewModel {
    lazy var weatherRepository = WeatherRepository()
    var weatherSubject: PublishSubject<[Weather]> = PublishSubject.init()
    var imageSubject: PublishSubject<UIImage> = PublishSubject.init()
    var errorSubject: PublishSubject<String> = PublishSubject.init()
    
    private let disposeBag = DisposeBag()
    
    func getWeather(locationId:Int) {
        self.weatherRepository
            .fetchWeather(id: locationId)
            .subscribe(onSuccess: {
                result in
                if let weather = result.value {
                    self.weatherSubject.onNext(weather.consolidated_weather)
                }
                if let error = result.error {
                    self.errorSubject.onNext(error.errorDetail ?? "unknown")
                }
            },onError: {
                error in
                self.errorSubject.onNext(error.localizedDescription) }
            ).disposed(by: disposeBag)
    }
    
    func getImage(name:String) -> Single<UIImage?> {
        return self.weatherRepository
            .fetchImage(name: name)
            .map({UIImage(data:$0)})
    }
}
