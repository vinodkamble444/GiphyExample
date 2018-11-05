package tk.andivinu.giphyexample.presentation.di.components;


import javax.inject.Singleton;

import dagger.Component;
import tk.andivinu.giphyexample.presentation.di.modules.GiphyModule;
import tk.andivinu.giphyexample.presentation.viewmodel.MainViewModel;

@Singleton @Component(modules = {GiphyModule.class})
public interface ApplicationComponent {
    void inject(MainViewModel viewModel);

}
