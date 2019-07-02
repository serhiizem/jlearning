import {Component, OnInit} from '@angular/core';
import 'rxjs/add/operator/finally';
import {WordsService} from "../shared/words.service";
import {LoaderService} from "../shared/loader.service";
import {AlertService} from "../shared/alert.service";

@Component({
  templateUrl: 'test.component.html',
  styleUrls: ['test.component.css']
})
export class TestComponent implements OnInit {

  constructor(private wordsService: WordsService,
              private loaderService: LoaderService,
              private alertService: AlertService) {
  }

  ngOnInit() {
  }

  private createFormVerbInputForm(): void {
  }
}
