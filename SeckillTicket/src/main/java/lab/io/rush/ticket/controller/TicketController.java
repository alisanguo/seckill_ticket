package lab.io.rush.ticket.controller;

import lab.io.rush.ticket.domain.Exposer;
import lab.io.rush.ticket.domain.SeckillExecution;
import lab.io.rush.ticket.domain.SeckillResult;
import lab.io.rush.ticket.domain.Ticket;
import lab.io.rush.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;
/**
 * Created by ali on 16-12-24.
 */

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<Ticket> list=ticketService.findAll();
        model.addAttribute("ticketList",list);
        return "list";
    }

    @RequestMapping(value = "/{ticketId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("ticketId") int ticketId, Model model){
        Ticket ticket=ticketService.findById(ticketId);
        model.addAttribute("ticket",ticket);
        return "detail";
    }

    @RequestMapping(value="/{ticketId}/{md5}/execution",method = RequestMethod.POST,
                    produces = "application/json;charset=utf-8")
    @ResponseBody
    public SeckillResult<SeckillExecution> executeSeckill(@PathVariable("ticketId")int ticketId,
                                                          @RequestParam("ticketName") String ticketName,
                @PathVariable("md5") String md5,@CookieValue(value = "killEmail",required = false) String email){
        if(true==false){
            return new SeckillResult(false,"您还未登录请先登录");
        }else if(null==email||"".equals(email)){
            return new SeckillResult(false,"您还未输入邮箱");
        }else{
            SeckillExecution result=ticketService.seckill(ticketId,ticketName,email,md5);
            return new SeckillResult<SeckillExecution>(true,result);
        }
    }


    @RequestMapping(value="/{ticketId}/exposer",method = RequestMethod.POST,
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public SeckillResult<Exposer> exposeUrl(@PathVariable("ticketId")int ticketId){
        return new SeckillResult<Exposer>(true,ticketService.exposeUrl(ticketId));
    }


    @RequestMapping(value="/nowTime",method = RequestMethod.GET,
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public SeckillResult<Long> nowTime(){
        return new SeckillResult<Long>(true,new Date().getTime());
    }


    @RequestMapping(value="/{ticketId}/number",method = RequestMethod.POST,
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public SeckillResult<Integer> number(@PathVariable("ticketId")int ticketId){
        return ticketService.getTicketNum(ticketId);
    }


}
