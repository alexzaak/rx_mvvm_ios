//
//  ViewController.swift
//  showcase_mvvm
//
//  Created by Zaak, Alexander on 20.06.19.
//  Copyright © 2019 Zaak, Alexander. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class ViewController: UIViewController {
    @IBOutlet weak var tableView: UITableView!

    private let disposeBag = DisposeBag()
    
    var viewModel: LocationViewModel = LocationViewModel()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.dataSource = nil
        self.tableView.delegate = nil

        let locations = self.viewModel.locationSubject.asObserver()

        locations.bind(to: tableView.rx.items(cellIdentifier: "Cell", cellType: LocationCell.self)){ (row, element, cell) in
            cell.titleLabel?.text = element.title
            }
            .disposed(by: disposeBag)

        tableView.rx
            .modelSelected(Location.self)
            .subscribe(onNext:  { value in
                self.performSegue(withIdentifier: "showWeather", sender: value)
            })
            .disposed(by: disposeBag)

        tableView.rx
            .itemAccessoryButtonTapped
            .subscribe(onNext: { indexPath in
                print("Tapped Detail @ \(indexPath.section),\(indexPath.row)")
            })
            .disposed(by: disposeBag)

        viewModel.getNearlyLocations()
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if (segue.identifier == "showWeather"){
            _ = WeatherViewController.storyboardInstance()
            let destinationController = segue.destination as? WeatherViewController

            if let model = sender as? Location {
                destinationController?.location = model
            }
        }
    }
}

