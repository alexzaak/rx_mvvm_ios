//
//  Double+format.swift
//  showcase_mvvm
//
//  Created by Zaak, Alexander on 22.06.19.
//  Copyright © 2019 Zaak, Alexander. All rights reserved.
//

import Foundation

extension Double {
    func format() -> String {
        return String(format: "%.2f", self)
    }
}
