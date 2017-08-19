package org.sample.multimodule;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.sample.multimodule.controller.UserController;
import org.sample.multimodule.model.User;
import org.sample.multimodule.service.UserService;
import org.sample.multimodule.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hibernate.validator.internal.metadata.raw.ConstrainedField;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

/**
 * Unit test for simple App.
 */

@SpringBootTest
@AutoConfigureRestDocs(outputDir="/target/generated-snippets")
@RunWith(SpringRunner.class)
public class AppTest 
   {
	
	@Autowired
	UserService service;
	@Autowired
	private ObjectMapper objectMapper;
	
	
	    private MockMvc mockMvc;
	
	 @Autowired
		private WebApplicationContext context;
	 
	 @Rule
		public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
	 
	 @Before
	 public void setUp() {
	 	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
	 			.apply(documentationConfiguration(this.restDocumentation)) 
	 			.build();
	 }
	 
	 @Test
  public void getAllUser() throws Exception{
		 CreateSampleUser("Jitin",23);
		 CreateSampleUser("Rahul",24);
		 CreateSampleUser("Akash",50);
		

		 this.mockMvc.perform(
				 get("/user/alluser").accept(MediaType.APPLICATION_JSON_VALUE)
				 ).andExpect(status().isOk()).andDo(document("getAllUser",
						 responseFields(fieldWithPath("[].id").description("This is user Id"),
								 		fieldWithPath("[].name").description("This is user name"),
								 		fieldWithPath("[].age").description("This is user age"))));
		 
		
					  
  }
	 @Test
	 public void getUserById() throws Exception{
		 User user= CreateSampleUser("Tom",24);
	
		 this.mockMvc.perform(get("/user/"+user.getId()).accept(MediaType.APPLICATION_JSON_VALUE))
		 				.andExpect(status().isOk()).andDo(document("getUserById"));
		 				
	 }
	 @Test
	 public void createUser() throws Exception{
		User user= new User("Harsh",27);
		
	FieldDescriptor[] userEntry=new FieldDescriptor[] {
			fieldWithPath("id").description("This is user Id"),
			fieldWithPath("name").description("This is user name"),
			fieldWithPath("age").description("This is user age")
					
	};
	 this.mockMvc.perform(post("/user/adduser").contentType(MediaType.APPLICATION_JSON_VALUE)
	 			.content(this.objectMapper.writeValueAsString(user))).andExpect(status().isCreated())
	 .andDo(document("createUser",requestFields(userEntry)));
	
	 } 
	 @Test
	 public void deleteUser() throws Exception{
		User user= CreateSampleUser("Jitin",23);
		
		 
			
			this.mockMvc.perform(delete("/user/delete/"+user.getId()).accept(MediaType.APPLICATION_JSON_VALUE)
					).andExpect(status().isOk()).andDo(document("deleteUser"));
	 }
	 
  private User CreateSampleUser(String name, int age) {
	 return service.createUser(new User(name, age));
  }
}
