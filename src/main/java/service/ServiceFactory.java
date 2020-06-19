package service;

        import service.action.Action;
        import service.action.ActionName;
        import service.action.analysis.AnalysisService;
        import service.action.caractions.*;
        import service.action.menu.*;
        import service.action.orderactions.*;
        import service.action.useractions.*;
        import service.action.useranduserinfoactions.*;

        import java.util.HashMap;
        import java.util.Map;

public class ServiceFactory {

    private static ServiceFactory serviceFactory = new ServiceFactory();
    private Map<ActionName, Action> map = new HashMap<>();
    private ServiceFactory(){
        map.put(ActionName.MAINPAGE,new MainPage());

        map.put(ActionName.ADDUSER,new AddUserService());
        map.put(ActionName.UPDATEUSER,new UpdateUserService());
        map.put(ActionName.DELETEUSER,new DeleteUserService());
        map.put(ActionName.READUSER,new ReadUserByIdService());
        map.put(ActionName.READALLUSER,new ReadAllUserService());

        map.put(ActionName.ADDUSERANDUSERINFO,new AddUserAndUserInfoService());
        map.put(ActionName.UPDATEUSERANDUSERINFO,new UpdateUserAndUserInfoService());
        map.put(ActionName.UPDATEPAGEUSERANDUSERINFO,new UpdatePageUserAndUserInfoService());
        map.put(ActionName.DELETEUSERANDUSERINFO,new DeleteUserAndUserInfoService());
        map.put(ActionName.READALLUSERANDUSERINFO,new ReadAllUserAndUserInfoService());
        map.put(ActionName.READUSERANDUSERINFO,new ReadUserAndUserInfoByIdService());

        map.put(ActionName.SEARCHUSER,new SearchUserService());
        map.put(ActionName.SORTUSER,new CompareUserService());

        map.put(ActionName.ADDCAR,new AddCarService());
        map.put(ActionName.UPDATECAR,new UpdateCarService());
        map.put(ActionName.UPDATEPAGECAR,new UpdateCarPageService());
        map.put(ActionName.DELETECAR,new DeleteCarService());
        map.put(ActionName.READCAR,new ReadCarByIdService());
        map.put(ActionName.READALLCARANDORDER,new ReadAllCarAndOrderService());
        map.put(ActionName.SEARCHCAR,new SearchCarService());
        map.put(ActionName.SORTCAR,new CompareCarService());

        map.put(ActionName.READALLORDER,new ReadAllOrderService());
        map.put(ActionName.MOREORDERINFO,new MoreOrderInfo());
        map.put(ActionName.UPDATEPAGEORDER,new UpdateOrderPageService());
        map.put(ActionName.UPDATEORDER,new UpdateOrderService());
        map.put(ActionName.DELETEORDER,new DeleteOrderService());
        map.put(ActionName.ADDORDER,new AddOrderService());
        map.put(ActionName.SORTORDER,new CompareOrderService());
        map.put(ActionName.SEARCHORDER,new SearchOrderService());
        map.put(ActionName.ADDORDERPAGE,new AddOrderPageService());

        map.put(ActionName.UPDATEMYSELFPAGE,new UpdateMySelfPageService());
        map.put(ActionName.UPDATEMYSELF,new UpdateMySelfService());
        map.put(ActionName.REGISTRATION,new RegistrationService());
        map.put(ActionName.LOGOUT,new LogoutService());
        map.put(ActionName.BLOCKUSER,new BlockUserService());
        map.put(ActionName.UNBLOCKUSER,new UnblockUserService());

        map.put(ActionName.PRINTCHECK,new SaveCheckInFileService());
        map.put(ActionName.PROFITANALYSIS,new AnalysisService());
        map.put(ActionName.FREECARS,new ReadFreeCarsService());


    }

    public Action getAction(ActionName name){
        return map.get(name);
    }

    public static ServiceFactory getInstance(){
        return serviceFactory;
    }
}
