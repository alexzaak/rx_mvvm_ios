//
//  ApiClient.swift
//  showcase_mvvm
//
//  Created by Zaak, Alexander on 21.06.19.
//  Copyright © 2019 Zaak, Alexander. All rights reserved.
//

import Foundation
import RxSwift
import RxRetroSwift

class ApiClient {
    static var shared = ApiClient()

    lazy var caller:RequestCaller = {
        let config = URLSessionConfiguration.default
        if #available(iOS 11.0, *) {
            config.waitsForConnectivity = true
        }
        return RequestCaller(config: config)
    }()

    private init() {
        RequestModel.defaults.baseUrl = "https://www.metaweather.com/api"
    }

    func fetchWeather(id:Int) -> Observable<Result<WeatherResult, ErrorModel>> {
        let request:URLRequest = RequestModel(
            httpMethod: .get,
            path: "location/\(id)")
            .asURLRequest()

        return caller.call(request)
    }

    func fetchLocations() -> Observable<Result<[Location], ErrorModel>> {
        let request:URLRequest = RequestModel(
            httpMethod: .get,
            path: "location/search",
            query: ["lattlong":"53.5582447,9.647645"])
            .asURLRequest()

        return caller.call(request)
    }

    func fetchImage(url: String) -> Observable<Data> {
        return URLSession.shared.rx
            .response(request: URLRequest(url: URL(string: url)!))
            .map({$0.data})
    }
}
