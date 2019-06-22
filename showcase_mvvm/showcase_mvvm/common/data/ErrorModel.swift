//
//  ErrorModel.swift
//  showcase_mvvm
//
//  Created by Zaak, Alexander on 20.06.19.
//  Copyright © 2019 Zaak, Alexander. All rights reserved.
//

import Foundation
import RxRetroSwift

struct ErrorModel:HasErrorInfo, Codable {

    var errorCode: Int?
    var errorDetail: String?
}
