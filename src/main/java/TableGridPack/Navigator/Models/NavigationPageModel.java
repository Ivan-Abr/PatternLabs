package TableGridPack.Navigator.Models;

import Main.UpdateDataInterface;

import java.util.LinkedList;

public class NavigationPageModel {
    public int currentPage = 1;
    public int maxCountOfPages;

    public int elementsPerPage = 10;

    public LinkedList<UpdateDataInterface> subscribersForPageUpdate = new LinkedList<>();
    public UpdateDataInterface navigatorSubscriber;

    public NavigationPageModel(int maxRecordsCount) {
        this.maxCountOfPages = (int) Math.ceil((double) maxRecordsCount / elementsPerPage);
    }

    public void setMaxCountOfPages(int maxRecordsCount) {
        this.maxCountOfPages = (int) Math.ceil((double) maxRecordsCount / elementsPerPage);
        this.notifyNavigatorSub();
    }

    public void subscribePageUpdate(UpdateDataInterface subscriber) {
        subscribersForPageUpdate.add(subscriber);
    }
    public void subscribeNavigatorUpdate(UpdateDataInterface subscriber) {
        navigatorSubscriber = subscriber;
    }

    public void notifyPageSubs() {
        for (UpdateDataInterface sub : this.subscribersForPageUpdate) {
            sub.updatePage();
        }
    }
    public void notifyNavigatorSub() {
        this.navigatorSubscriber.updatePage();
    }


    public void nextPage() {
        if (this.currentPage < this.maxCountOfPages) {
            this.currentPage += 1;
            this.notifyPageSubs();
            this.notifyNavigatorSub();
        }
    }

    public void prevPage() {
        if (this.currentPage > 1) {
            this.currentPage -= 1;
            this.notifyPageSubs();
            this.notifyNavigatorSub();
        }
    }

    public void elementPerPageSelected(int elementsPerPage) {
        this.elementsPerPage = elementsPerPage;
        this.currentPage = 1;
        this.notifyPageSubs();
        this.notifyNavigatorSub();
    }

    public void setDefaultWithoutNotify() {
        this.currentPage = 1;
        this.elementsPerPage = 10;
    }

    public void setDefaultParams() {
        this.setDefaultWithoutNotify();
        this.notifyPageSubs();
        this.notifyNavigatorSub();
    }
}
