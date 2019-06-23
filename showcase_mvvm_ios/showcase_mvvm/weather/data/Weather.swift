//
//  Weather.swift
//  showcase_mvvm
//
//  Created by Zaak, Alexander on 21.06.19.
//  Copyright © 2019 Zaak, Alexander. All rights reserved.
//

import Foundation

struct WeatherResult:Codable {
    var title:String = ""
    var consolidated_weather:[Weather] = []
}

struct Weather:Codable {
    var weather_state_name:String = ""
    var weather_state_abbr:String = ""
    var applicable_date:String = ""
    var min_temp:Double = 0.0
    var max_temp:Double = 0.0
}
