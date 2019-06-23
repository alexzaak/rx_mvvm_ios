//
//  WeatherViewController.swift
//  showcase_mvvm
//
//  Created by Zaak, Alexander on 21.06.19.
//  Copyright © 2019 Zaak, Alexander. All rights reserved.
//

import UIKit
import RxSwift

class WeatherViewController: UIViewController {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var tableView: UITableView!

    var location: Location? = nil
    var viewModel: WeatherViewModel = WeatherViewModel()

    private let disposeBag = DisposeBag()

    static func storyboardInstance() -> WeatherViewController? {
        let storyboard = UIStoryboard(name: "Weather", bundle: nil)
        return storyboard.instantiateViewController(withIdentifier: "WeatherViewController") as? WeatherViewController
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        self.tableView.dataSource = nil
        self.tableView.delegate = self

        let weather = self.viewModel.weatherSubject.asObserver()
        weather.bind(to: tableView.rx.items(cellIdentifier: "WeatherCell", cellType: WeatherCell.self)) { (row, element, cell) in
            cell.setIcon(iconName: element.iconUrl)
            cell.setState(stateName: element.state)
            }
            .disposed(by: disposeBag)

        self.titleLabel.text = self.location?.title
        self.viewModel.getWeather(locationId: self.location?.woeid ?? 0)
    }
}

extension WeatherViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100.0
    }
}
