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
    var weatherSubject: PublishSubject<[Forecast]> = PublishSubject.init()
    var imageSubject: PublishSubject<UIImage> = PublishSubject.init()
    var errorSubject: PublishSubject<String> = PublishSubject.init()
    
    private let disposeBag = DisposeBag()
    
    func getWeather(locationId:Int) {
        self.weatherRepository
            .fetchWeather(id: locationId)
            .observeOn(Schedulers.shared.mainScheduler)
            .map({$0.value?.map({Forecast(state: $0.weather_state_name, formatedDate: $0.applicable_date, iconName: $0.weather_state_abbr, maxTemp: $0.max_temp)})})
            .subscribe(onSuccess: {
                data in
                if let forecast = data {
                    self.weatherSubject.onNext(forecast)
                } else {
                    self.errorSubject.onNext("unknown")
                }
            },onError: {
                error in
                self.errorSubject.onNext(error.localizedDescription) }
            ).disposed(by: disposeBag)
    }
    
    func getImage(name:String) -> Single<UIImage?> {
        return self.weatherRepository
            .fetchImage(url: name)
            .observeOn(Schedulers.shared.mainScheduler)
            .map({UIImage(data:$0)})
    }
}

struct Forecast {
    let state: String
    let iconUrl: String
    let maxTemp: String

    init(state: String, formatedDate: String, iconName: String, maxTemp: Double) {
        self.state = "\(state) for \(formatedDate)"
        self.iconUrl = "https://www.metaweather.com/static/img/weather/png/64/\(iconName).png"
        self.maxTemp = "\(maxTemp.format())"
    }
}
