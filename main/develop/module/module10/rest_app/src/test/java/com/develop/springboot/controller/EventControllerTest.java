package com.develop.springboot.controller;

import com.develop.springboot.controller.api.ApiEventController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(ApiEventController.class)
public class EventControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private WebApplicationContext context;
//
//    @MockBean
//    private BookingFacade bookingFacade;
//
//    private EventDTO mockEvent;
//
//    @BeforeEach
//    void setUp() {
//        mockEvent = new EventDTO();
//        mockEvent.setId(UUID.randomUUID());
//        mockEvent.setTitle("Mock Event");
//        mockEvent.setDate(LocalDateTime.now());
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity()) // Apply Spring Security to MockMvc
//                .build();
//    }
//
//    @Test
//    public void testCreateEvent() throws Exception {
//        // assuming bookingFacade.saveEvent() doesn't return anything
//        mockMvc.perform(post("/api/events")
//                        .contentType("application/json")
//                        .content("{\"name\":\"Mock Event\"}")) // This is a simplified payload. Modify accordingly.
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void testGetEventById() throws Exception {
//        when(bookingFacade.findEventById(any(UUID.class))).thenReturn(Optional.of(mockEvent));
//
//        mockMvc.perform(get("/api/events/" + mockEvent.getId()))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"id\":\"" + mockEvent.getId() + "\",\"title\":\"Mock Event\"}"));
//    }
//
//    @Test
//    public void testGetAllEvents() throws Exception {
//        List<EventDTO> events = List.of(mockEvent);
//        when(bookingFacade.findAllEvents()).thenReturn(events);
//
//        mockMvc.perform(get("/api/events"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{\"id\":\"" + mockEvent.getId() + "\",\"title\":\"Mock Event\"}]"));
//    }
//
//    @Test
//    public void testUpdateEvent() throws Exception {
//        doNothing().when(bookingFacade).saveEvent(any(EventDTO.class));
//
//        mockMvc.perform(put("/api/events/" + mockEvent.getId())
//                        .contentType("application/json")
//                        .content("{\"id\":\"" + mockEvent.getId() + "\",\"title\":\"Updated Event\"}"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"id\":\"" + mockEvent.getId() + "\",\"title\":\"Updated Event\"}"));
//    }
//
//    @Test
//    public void testDeleteEvent() throws Exception {
//        // assuming bookingFacade.deleteEventById() doesn't return anything
//        mockMvc.perform(delete("/api/events/" + mockEvent.getId()))
//                .andExpect(status().isNoContent());
//    }

}
