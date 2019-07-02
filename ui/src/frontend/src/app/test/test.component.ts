import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import 'rxjs/add/operator/finally';
import {WordsService} from "../shared/words.service";
import {LoaderService} from "../shared/loader.service";
import {Observable} from "rxjs/Observable";
import {map} from "rxjs/operators";
import {WordGroup} from "../shared/types";
import {FormBuilder, FormGroup, FormGroupDirective, Validators} from "@angular/forms";
import {AlertService} from "../shared/alert.service";
import {VERB_ADDED} from "../shared/messages";

export class TranslatableWord {
  value: string = '';
  translations: string[] = [];
}

@Component({
  templateUrl: 'words.component.html',
  styleUrls: ['words.component.css']
})
export class WordsComponent implements OnInit {
  @ViewChild('fileInput') fileInput: ElementRef;
  imgSrc: any = {};
  imageCropStep: number = 0;
  imageChangedEvent: any = '';
  croppedImage: any = '';

  verbInputForm: FormGroup;

  translatableWord: TranslatableWord;
  translation: string = '';

  wordGroups: Observable<WordGroup[]>;

  constructor(private wordsService: WordsService,
              private loaderService: LoaderService,
              private alertService: AlertService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.wordGroups = this.wordsService.wordGroupsState
      .pipe(
        map(value => value)
      );

    this.loaderService.show();
    this.wordsService.loadUserVerbs().subscribe(() => {
      this.loaderService.hide();
    });
    this.createFormVerbInputForm();

    this.translatableWord = new TranslatableWord();
  }

  private createFormVerbInputForm(): void {
    this.verbInputForm = this.formBuilder.group({
      word: ['', Validators.required],
      translations: ['', Validators.required]
    });
  }

  addVerb(formDirective: FormGroupDirective): void {
    this.loaderService.show();
    this.wordsService.addVerb(this.translatableWord).subscribe(() => {
      this.wordsService.loadUserVerbs().subscribe(() => {
        this.resetForm(formDirective);
        this.loaderService.hide();
        this.alertService.success(VERB_ADDED);
      });
    });
  }

  private resetForm(formDirective: FormGroupDirective): void {
    this.translatableWord.value = '';
    this.translatableWord.translations = [];
    this.translation = '';
    formDirective.resetForm();
    this.verbInputForm.reset();
  }

  addTranslation(translationOption: string) {
    this.translatableWord.translations.push(translationOption);
  }

  uploadPicture(): void {
    this.fileInput.nativeElement.click();
  }

  imageLoaded() {
  }

  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
    this.imageCropStep = 2;
  }

  imageCropped(image: string) {
    this.croppedImage = image;
  }

  loadImageFailed() {
    // show message
  }

  crop() {
    this.imageCropStep = 3;
  }

  clear() {
    this.imageCropStep = 1;
    this.fileInput.nativeElement.value = '';
    delete this.imgSrc;
    this.imgSrc = {};
  }
}
