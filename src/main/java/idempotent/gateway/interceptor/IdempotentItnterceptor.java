package idempotent.gateway.interceptor;

import java.util.Optional;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import idempotent.gateway.model.IdempotentCheckModel;
import idempotent.gateway.service.IdempotentService;
import idempotent.gateway.util.Status;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class IdempotentItnterceptor implements HandlerInterceptor {

    private IdempotentService idempotentService;

    public IdempotentItnterceptor (IdempotentService idempotentService){
        this.idempotentService = idempotentService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String key = request.getHeader("key");

        //When key is not present
        if(key == null || key.isEmpty()){
            response.setStatus(400);
            response.getWriter().write("Missing idempotency key");
            return false;
        }

        //When key is present
        request.setAttribute("Key", key);

        //if key is already in idempotency table
        Optional <IdempotentCheckModel> idempotentRecordOpt = idempotentService.findByKey(key);
        if(idempotentRecordOpt.isPresent()){
            IdempotentCheckModel idempotentRec = idempotentRecordOpt.get();
            Status status = idempotentRec.getStatus();
            if(status== Status.PROCESSING){
            response.getWriter().write("Your request is processing");
            return false;
            }

            if(status == Status.SUCCESS){
            response.getWriter().write("Your request is already successfully processed");
            return false;
            }
        }

        //if key is not in idempotency table
        idempotentService.setProcessing(key);
        Thread.sleep(6000);
        return true;
        
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {

        String key = (String) request.getAttribute("Key");
        if(key==null){
            return;
        }

        try{
            if(ex == null){
                //mark it from processing to success

                idempotentService.markSuccess(key);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}
    
}
