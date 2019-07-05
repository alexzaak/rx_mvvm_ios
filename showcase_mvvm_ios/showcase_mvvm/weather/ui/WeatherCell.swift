//
//  WeatherCell.swift
//  showcase_mvvm
//
//  Created by Zaak, Alexander on 21.06.19.
//  Copyright © 2019 Zaak, Alexander. All rights reserved.
//

import UIKit
import RxSwift

class WeatherCell: UITableViewCell {
    @IBOutlet weak var stateLable: UILabel!
    @IBOutlet weak var iconView: UIImageView!

    var viewModel: WeatherViewModel = WeatherViewModel()
    var model: Forecast? = nil

    var disposeBag:DisposeBag = DisposeBag()

    override func prepareForReuse() {
        disposeBag = DisposeBag()
    }

    override func awakeFromNib() {
        super.awakeFromNib()
    }

    func setState(stateName: String) {
        self.stateLable.text = stateName
    }

    func setIcon(iconName: String) {
        self.viewModel.getImage(name: iconName)
            .subscribe(onSuccess: { (image) in
                self.iconView.image = image
            },onError: { (error) in
                print(error)
            }).disposed(by: self.disposeBag)
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
}
